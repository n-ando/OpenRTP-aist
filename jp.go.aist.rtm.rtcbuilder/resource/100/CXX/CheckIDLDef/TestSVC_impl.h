// -*-C++-*-
/*!
 * @file  TestSVC_impl.h
 * @brief Service implementation header of Test.idl
 *
 */

#include "TestSkel.h"

#ifndef TESTSVC_IMPL_H
#define TESTSVC_IMPL_H
 
/*!
 * @class MyModule_MyInterfaceSVC_impl
 * Example class implementing IDL interface MyModule::MyInterface
 */
class MyModule_MyInterfaceSVC_impl
 : public virtual POA_MyModule::MyInterface,
   public virtual PortableServer::RefCountServantBase
{
 private:
   // Make sure all instances are built on the heap by making the
   // destructor non-public
   //virtual ~MyModule_MyInterfaceSVC_impl();

 public:
  /*!
   * @brief standard constructor
   */
   MyModule_MyInterfaceSVC_impl();
  /*!
   * @brief destructor
   */
   virtual ~MyModule_MyInterfaceSVC_impl();

   // attributes and operations
   void op1();
   ::CORBA::Short op2(const MyModule::MyStruct& mydata);
   void op3();
   void op4(::CORBA::Short inshort, ::CORBA::Short& outshort, ::CORBA::Short& ioshort);
   void op5(RTC::Point3D inpoint3d);
   void op6(RTC::Point3D& outpoint3d);

};



#endif // TESTSVC_IMPL_H


