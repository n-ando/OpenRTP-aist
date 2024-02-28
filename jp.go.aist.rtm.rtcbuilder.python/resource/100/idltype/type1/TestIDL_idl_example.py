#!/usr/bin/env python
# -*- coding: utf-8 -*-
# -*- Python -*-
"""
 @file TestIDL_idl_example.py
 @brief Python example implementations generated from TestIDL.idl
 @date $Date$
"""
import omniORB
from omniORB import CORBA, PortableServer
import _GlobalIDL, _GlobalIDL__POA
class ComFk_i (_GlobalIDL__POA.ComFk):
    """
    @class ComFk_i
    Example class implementing IDL interface ComFk
    """
    def __init__(self):
        """
        @brief standard constructor
        Initialise member variables here
        """
        pass
    # ValueList set_value(in ValueList frm, out ValueList frmo, inout ValueList frmio)
    def set_value(self, frm, frmio):
        raise CORBA.NO_IMPLEMENT(0, CORBA.COMPLETED_NO)
        # *** Implement me
        # Must return: result, frmo, frmio
    # StringList set_string(in StringList frm, out StringList frmo, inout StringList frmio)
    def set_string(self, frm, frmio):
        raise CORBA.NO_IMPLEMENT(0, CORBA.COMPLETED_NO)
        # *** Implement me
        # Must return: result, frmo, frmio
    # LongArray set_longArray(in LongArray frm, out LongArray frmo, inout LongArray frmio)
    def set_longArray(self, frm, frmio):
        raise CORBA.NO_IMPLEMENT(0, CORBA.COMPLETED_NO)
        # *** Implement me
        # Must return: result, frmo, frmio
    # ValueListArray set_longArrayv(in ValueListArray frm, out ValueListArray frmo, inout ValueListArray frmio)
    def set_longArrayv(self, frm, frmio):
        raise CORBA.NO_IMPLEMENT(0, CORBA.COMPLETED_NO)
        # *** Implement me
        # Must return: result, frmo, frmio
    # color set_color(in color frm, out color frmo, inout color frmio)
    def set_color(self, frm, frmio):
        raise CORBA.NO_IMPLEMENT(0, CORBA.COMPLETED_NO)
        # *** Implement me
        # Must return: result, frmo, frmio
    # Frame0 set_tool0(in Frame0 frm, out Frame0 frmo, inout Frame0 frmio)
    def set_tool0(self, frm, frmio):
        raise CORBA.NO_IMPLEMENT(0, CORBA.COMPLETED_NO)
        # *** Implement me
        # Must return: result, frmo, frmio
    # Frame1 set_tool1(in Frame1 frm, out Frame1 frmo, inout Frame1 frmio)
    def set_tool1(self, frm, frmio):
        raise CORBA.NO_IMPLEMENT(0, CORBA.COMPLETED_NO)
        # *** Implement me
        # Must return: result, frmo, frmio
    # Frame2 set_tool2(in Frame2 frm, out Frame2 frmo, inout Frame2 frmio)
    def set_tool2(self, frm, frmio):
        raise CORBA.NO_IMPLEMENT(0, CORBA.COMPLETED_NO)
        # *** Implement me
        # Must return: result, frmo, frmio
    # Frame3 set_tool3(in Frame3 frm, out Frame3 frmo, inout Frame3 frmio)
    def set_tool3(self, frm, frmio):
        raise CORBA.NO_IMPLEMENT(0, CORBA.COMPLETED_NO)
        # *** Implement me
        # Must return: result, frmo, frmio
    # Frame4 set_tool4(in Frame4 frm, out Frame4 frmo, inout Frame4 frmio)
    def set_tool4(self, frm, frmio):
        raise CORBA.NO_IMPLEMENT(0, CORBA.COMPLETED_NO)
        # *** Implement me
        # Must return: result, frmo, frmio
    # Frame5 set_tool5(in Frame5 frm, out Frame5 frmo, inout Frame5 frmio)
    def set_tool5(self, frm, frmio):
        raise CORBA.NO_IMPLEMENT(0, CORBA.COMPLETED_NO)
        # *** Implement me
        # Must return: result, frmo, frmio
    # Frame1List set_toolSeq1(in Frame1List frm, out Frame1List frmo, inout Frame1List frmio)
    def set_toolSeq1(self, frm, frmio):
        raise CORBA.NO_IMPLEMENT(0, CORBA.COMPLETED_NO)
        # *** Implement me
        # Must return: result, frmo, frmio
    # Frame2List set_toolSeq2(in Frame2List frm, out Frame2List frmo, inout Frame2List frmio)
    def set_toolSeq2(self, frm, frmio):
        raise CORBA.NO_IMPLEMENT(0, CORBA.COMPLETED_NO)
        # *** Implement me
        # Must return: result, frmo, frmio
    # Frame1Array set_toolArray1(in Frame1Array frm, out Frame1Array frmo, inout Frame1Array frmio)
    def set_toolArray1(self, frm, frmio):
        raise CORBA.NO_IMPLEMENT(0, CORBA.COMPLETED_NO)
        # *** Implement me
        # Must return: result, frmo, frmio
    # Frame2Array set_toolArray2(in Frame2Array frm, out Frame2Array frmo, inout Frame2Array frmio)
    def set_toolArray2(self, frm, frmio):
        raise CORBA.NO_IMPLEMENT(0, CORBA.COMPLETED_NO)
        # *** Implement me
        # Must return: result, frmo, frmio
if __name__ == "__main__":
    import sys
    
    # Initialise the ORB
    orb = CORBA.ORB_init(sys.argv)
    
    # As an example, we activate an object in the Root POA
    poa = orb.resolve_initial_references("RootPOA")
    # Create an instance of a servant class
    servant = ComFk_i()
    # Activate it in the Root POA
    poa.activate_object(servant)
    # Get the object reference to the object
    objref = servant._this()
    
    # Print a stringified IOR for it
    print(orb.object_to_string(objref))
    # Activate the Root POA's manager
    poa._get_the_POAManager().activate()
    # Run the ORB, blocking this thread
    orb.run()
