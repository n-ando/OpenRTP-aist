package jp.go.aist.rtm.rtcbuilder.generator;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.IDLParser;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.ParseException;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.Node;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.NodeToken;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.array_declarator;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.base_type_spec;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.definition;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.enum_type;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.identifier;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.interface_dcl;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.interface_header;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.module;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.op_dcl;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.param_dcl;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.scoped_name;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.sequence_type;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.simple_type_spec;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.specification;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.string_type;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.struct_type;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.type_declarator;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.visitor.DepthFirstVisitor;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.visitor.GJNoArguDepthFirst;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.visitor.GJVoidDepthFirst;
import jp.go.aist.rtm.rtcbuilder.generator.param.DataTypeParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.ServiceArgumentParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.ServiceClassParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.ServiceMethodParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.TypeDefParam;

/**
 * IDLの構文解析木から、ジェネレータのインプットとなる情報に変換するクラス
 * <p>
 */
public class IDLParamConverter {

	static private List<String> moduleName;

	/**
	 * IDLの構文解析木から、ジェネレータのインプットとなるServiceParamに変換する
	 *
	 * @param spec
	 * @return ServiceParam
	 */
	public static List<ServiceClassParam> convert(specification spec,
			final String idlPath) {
		final List<ServiceClassParam> result = new ArrayList<ServiceClassParam>();
		spec.accept(new GJVoidDepthFirst<String>() {
			@SuppressWarnings("unchecked")
			@Override
			public void visit(interface_dcl n, String argu) {
				final InterfaceInfomation interfaceInfomation = new InterfaceInfomation();

				interface_header interfaceHeader = n.interface_header;

				interfaceInfomation.name = interfaceHeader.identifier.nodeToken.tokenImage;

				n.interface_header.accept(
						new GJVoidDepthFirst<InterfaceInfomation>() {
							@Override
							public void visit(scoped_name n,
									InterfaceInfomation argu) {
								interfaceInfomation.superInterfaceList
										.add(node2String(n));
							}
						}, interfaceInfomation);

				final ServiceClassParam service = new ServiceClassParam();
				service.setIdlPath(idlPath);
				service.setModule(getModuleName(n));
				service.setName(getModuleName(n) + interfaceInfomation.name);
				service.getSuperInterfaceList().addAll(interfaceInfomation.superInterfaceList);

				n.interface_body.accept(new GJVoidDepthFirst() {
					@Override
					public void visit(op_dcl n, Object argu) {
						final ServiceMethodParam serviceMethodParam = new ServiceMethodParam();
						serviceMethodParam.setName(n.identifier.nodeToken.tokenImage);
						serviceMethodParam.setType(node2String(n.op_type_spec));
						serviceMethodParam.setModule(service.getModule());

						n.parameter_dcls.accept(new GJVoidDepthFirst() {
							@Override
							public void visit(param_dcl n, Object argu) {
								ServiceArgumentParam serviceArgumentParam = new ServiceArgumentParam();
								serviceArgumentParam
										.setName(n.simple_declarator.identifier.nodeToken.tokenImage);
								serviceArgumentParam
										.setType(node2String(n.param_type_spec));
								serviceArgumentParam.setDirection(node2String(n.param_attribute));
								serviceArgumentParam.setModule(service.getModule());

								serviceMethodParam.getArguments().add(
										serviceArgumentParam);
							}
						}, null);

						service.getMethods().add(serviceMethodParam);
					}
				}, null);

				result.add(service);
			}

		}, null);
		checkSuperInterface(result, true);

		return result;
	}

	public static boolean checkSuperInterface(List<ServiceClassParam> targetList, boolean isAdd) {
		for(ServiceClassParam target : targetList) {
			if( target.getSuperInterfaceList().size()==0 ) continue;
			boolean isHit = false;
			for(String targetIF : target.getSuperInterfaceList()) {
				for(ServiceClassParam source : targetList) {
					if(targetIF.equals(source.getName())) {
						if(isAdd) {
							target.getMethods().addAll(source.getMethods());
						}
						target.setTypeDef(source.getTypeDefList());
						isHit = true;
						break;
					}
				}
				if(isHit==false) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * IDLの構文解析木から、sequence型のtypedefを探索する
	 *
	 * @param spec
	 * @return HashMap
	 */
	public static List<TypeDefParam> convert_typedef(specification spec,
			final String idlPath) {
		final List<TypeDefParam> result = new ArrayList<TypeDefParam>();
		moduleName = new ArrayList<String>();

		spec.accept(new GJVoidDepthFirst<String>() {

			@Override
			public void visit(module n, String argu) {
				moduleName.add(node2String(n.identifier.nodeToken));
				super.visit(n, argu);
			}

			@Override
			public void visit(NodeToken n, String argu) {
				if( node2String(n).equals("}")) {
					moduleName.remove(moduleName.size()-1);
				}
				super.visit(n, argu);
			}

			@Override
			public void visit(interface_dcl n, String argu) {
				final String ifname = n.interface_header.identifier.nodeToken.tokenImage;
				final TypeDefParam tdparam = new TypeDefParam();
				tdparam.setModuleName(getModuleNames());
				tdparam.setInterface(true);
				tdparam.setTargetDef(ifname);
				result.add(tdparam);

				n.interface_body.accept(new GJVoidDepthFirst<String>() {
					@Override
					public void visit(type_declarator n, String argu) {
						final TypeDefParam tdparam = new TypeDefParam();
						tdparam.setModuleName(getModuleNames());
						tdparam.setAlias(true);

						n.declarators.accept(new DepthFirstDeclaratorsVisitor(tdparam, ifname));
						n.type_spec.accept(new DepthFirstTypeSpecVisitor(tdparam));

						result.add(tdparam);
					}
				}, null);
			}
			@Override
			public void visit(struct_type n, String argu) {
				final TypeDefParam tdparam = new TypeDefParam();
				tdparam.setModuleName(getModuleNames());
				tdparam.setStruct(true);
				n.identifier.accept(new DepthFirstVisitor(){
					@Override
					public void visit(identifier n) {
						tdparam.setTargetDef(node2String(n));
					}
				});
				n.member_list.accept(new DepthFirstStructMemberVisitor(tdparam));

				result.add(tdparam);
			}
			@Override
			public void visit(enum_type n, String argu) {
				final TypeDefParam tdparam = new TypeDefParam();
				tdparam.setModuleName(getModuleNames());
//				tdparam.setEnum(true);
				n.identifier.accept(new DepthFirstVisitor(){
					@Override
					public void visit(identifier n) {
						tdparam.setTargetDef(node2String(n));
					}
				});
				result.add(tdparam);
			}
			@Override
			public void visit(type_declarator n, String argu) {
				final TypeDefParam tdparam = new TypeDefParam();
				tdparam.setModuleName(getModuleNames());

				n.declarators.accept(new DepthFirstDeclaratorsVisitor(tdparam));
				n.type_spec.accept(new DepthFirstTypeSpecVisitor(tdparam));

				result.add(tdparam);
			}
		}, null);
		return result;
	}

	private static String getModuleNames() {
		StringBuilder builder = new StringBuilder();
		for(int index=0;index<moduleName.size();index++) {
			builder.append(moduleName.get(index));
			if(index!=moduleName.size()-1) builder.append("::");
		}
		return builder.toString();
	}

	public static boolean extractTypeDef(List<DataTypeParam> sources, List<String> result, StringBuilder builder) {
		boolean ret = true;
		for( Iterator<DataTypeParam> iter = sources.iterator(); iter.hasNext(); ) {
			DataTypeParam targetParam = iter.next();
			String targetContent = targetParam.getContent();
			targetContent = PreProcessor.parseAlltoSpace(targetContent);
			IDLParser parser = new IDLParser(new StringReader(targetContent));
			specification spec=null;
			try {
				spec = parser.specification();
			} catch (ParseException e) {
				builder.append("Target File : ").append(targetParam.getDispPath()).append(System.lineSeparator());
				builder.append(e.getMessage()).append(System.lineSeparator());
				ret = false;
				continue;
			}
			List<String> types = parseForTypeDef(spec);
			for( Iterator<String> iterRes = types.iterator(); iterRes.hasNext(); ) {
				String resultType = iterRes.next();
				targetParam.getDefinedTypes().add(resultType);
				if( result.contains(resultType) ) {
					continue;
				}
				result.add(resultType);
			}
		}
		return ret;
	}

	private static List<String> parseForTypeDef(specification spec) {
		final List<String> results = new ArrayList<String>();

		spec.accept(new GJVoidDepthFirst<String>() {
			@SuppressWarnings("unchecked")
			@Override
			public void visit(module n, String argu) {
				moduleName = new ArrayList<String>();
				final String moduleNames = node2String(n.identifier);
				moduleName.add(moduleNames);
				n.accept(new ExtractModule(results),null);
			}
			//
			@Override
			public void visit(struct_type n, String argu) {
				String typeName = node2String(n.identifier);
				results.add(typeName);
			}
		}, null);

		return results;
	}
	private static final class ExtractModule extends GJVoidDepthFirst {
		private List<String> results;

		public ExtractModule(List<String> results) {
			this.results = results;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void visit(definition n, Object argu) {
			n.accept(new GJVoidDepthFirst() {
				@Override
				public void visit(struct_type n, Object argu) {
					String typeName = node2String(n.identifier);
					results.add(getModuleNames() + "::" + typeName);
				}
				@Override
				public void visit(module n, Object argu) {
					String typeName = node2String(n.identifier);
					moduleName.add(typeName);
					n.accept(new ExtractModule(results),null);
				}
			},null);
		}
	}

	/**
	 * インタフェースのモジュール名を取得する
	 *
	 * @param n
	 * @return
	 */
	private static String getModuleName(interface_dcl n) {
		String result = "";

		Node node = n;
		while (node != null) {
			if (node instanceof module) {
				result = node2String(((module) node).identifier) + "::" + result;
			}
			node = node.getParent();
		}

		return result;
	}

	/**
	 * ノードを文字列に変換する
	 *
	 * @param n
	 *            ノード
	 * @return 変換結果の文字列
	 */
	@SuppressWarnings("unchecked")
	public static String node2String(Node n) {
		final StringBuffer result = new StringBuffer();
		n.accept(new GJNoArguDepthFirst() {
			@Override
			public Object visit(NodeToken n) {
				return result.append(n.tokenImage);
			}
		});

		return result.toString();
	}

	/**
	 * IDLParamConverterクラスが内部で一時的に使用する、IDLのインタフェース表現。
	 * <p>
	 * 内部クラスであり大きな問題もないので、属性をpublicとしている。
	 */
	public static class InterfaceInfomation {
		public String name;

		public List<String> superInterfaceList = new ArrayList<String>();
	}

	/*
	 *  Custom Visitor
	 */
	/*
	 * Visitor for 'declarators()'
	 *    declarators() -> declarator() ( ',' declarator() )*
	 *        declarator() -> complex_declarator(), simple_declarator()
	 *            complex_declarator() -> array_declarator()
	 *                array_declarator() -> identifier() ( fixed_array_size() )+
	 *            simple_declarator() -> identifier()
	 *
	 */
	public static class DepthFirstDeclaratorsVisitor extends  DepthFirstVisitor{
		TypeDefParam tdparam;
		String ifname = null;

		public DepthFirstDeclaratorsVisitor() {
		}

		public DepthFirstDeclaratorsVisitor(TypeDefParam tdparam) {
		   this.tdparam = tdparam;
		}

		public DepthFirstDeclaratorsVisitor(TypeDefParam tdparam, String ifname) {
		   this.tdparam = tdparam;
		   this.ifname = ifname;
		}

		public void visit(identifier n) {
			if(ifname != null){ tdparam.setScopedName(ifname); }
			tdparam.setTargetDef(node2String(n));
		}

		public void visit(array_declarator n) {
			tdparam.setArray(true);
			if(ifname != null){ tdparam.setScopedName(ifname); }
			tdparam.setTargetDef(n.identifier.nodeToken.toString());
			tdparam.setArrayDim(n.nodeList.size());
		}
	}
  /**
   * Visitor for 'type_spec()'
   *
   *  'typedef' type_declarator()
   *  type_declarator() -> type_spec() declarators()
   *
   *  type_spec() -> simple_type_spec(), constr_type_spec()
   *                 constr_type_spec() -> struct_type(), union_type(), enum_type()
   *
   */
	public static class DepthFirstTypeSpecVisitor extends  DepthFirstVisitor{
		TypeDefParam tdparam;
		boolean replaceStringFlag;

		public DepthFirstTypeSpecVisitor() {
			replaceStringFlag = false;
		}
		public DepthFirstTypeSpecVisitor(TypeDefParam tdparam) {
			this.tdparam = tdparam;
		}
		public DepthFirstTypeSpecVisitor(TypeDefParam tdparam, boolean flag) {
			this.tdparam = tdparam;
			this.replaceStringFlag = flag;
		}

		////////
		//
		public void visit(simple_type_spec n) {
			n.nodeChoice.accept(new DepthFirstSimpleTypeSpecVisitor(this.tdparam, this.replaceStringFlag));
		}

		/////////////////
		public void visit(struct_type n) {
			tdparam.setModuleName(getModuleNames());
			tdparam.setStruct(true);
			n.member_list.accept(new DepthFirstStructMemberVisitor(tdparam));
			super.visit(n);
		}

		public void visit(enum_type n) {
			System.out.println("Call enum: "+ node2String(n.identifier));
			super.visit(n);
		}
		//////////////////
	}

	/*
	 * type_spec()
	 *   -> simple_type_spec()
	 *        -> base_type_spec(),
	 * 		  	   template_type_spec(),
	 * 			     scoped_name()
	 *
	 *           base_type_spec() -> floating_pt_type(),
	 *                         		   integer_type(),
	 *                          		 char_type(),
	 *                           		 boolean_type(),
	 *                           		 octet_type(),
	 *                           		 any_type()
	 *
	 *           template_type_spec() -> sequence_type(),
	 *                                   string_type()
	 *
	 *           scoped_name()
	 */
	public static class DepthFirstSimpleTypeSpecVisitor extends  DepthFirstVisitor{
		TypeDefParam tdparam;
		boolean replaceStringFlag;

		public DepthFirstSimpleTypeSpecVisitor() {
			replaceStringFlag = false;
		}

		public DepthFirstSimpleTypeSpecVisitor(TypeDefParam tdparam) {
			this.tdparam = tdparam;
		}

		public DepthFirstSimpleTypeSpecVisitor(TypeDefParam tdparam, boolean flag) {
			this.tdparam = tdparam;
			this.replaceStringFlag = flag;
		}

		///////
		/*
		 * sequence_type() -> 'sequence' '<' simple_type_spec() [ ',' positive_int_const() ] '>'
		 */
		@Override
		public void visit(simple_type_spec n) {
			tdparam.setOriginalDef(node2String(n));
			super.visit(n);
		}

		@Override
		public void visit(sequence_type n) {
			tdparam.setSequence(true);
			tdparam.setUnbounded(true);
			super.visit(n);
		}

		@Override
		public void visit(base_type_spec n) {
			String type = node2String(n);

			tdparam.setOriginalDef(type);
			super.visit(n);
		}

		@Override
		public void visit(scoped_name n) {
			String type = node2String(n);

			tdparam.setOriginalDef(type);
			tdparam.setAlias(true);
			super.visit(n);
		}

		@Override
		public void visit(string_type n) {
			String type = node2String(n);

			tdparam.setOriginalDef(type);
			super.visit(n);
		}
	}
/*
 *
 */
	public static class DepthFirstStructMemberVisitor extends  DepthFirstVisitor{
		TypeDefParam tdparam;

		public DepthFirstStructMemberVisitor() {
		}

		public DepthFirstStructMemberVisitor(TypeDefParam tdparam) {
			this.tdparam = tdparam;
		}
		@Override
		public void visit(simple_type_spec n) {
			tdparam.getChildType().add(node2String(n));
			super.visit(n);
		}

		@Override
		public void visit(sequence_type n) {
			tdparam.setUnbounded(true);
			super.visit(n);
		}

		@Override
		public void visit(string_type n) {
			tdparam.setUnbounded(true);
			super.visit(n);
		}
	}
}
