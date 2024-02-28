// -*-C++-*-
/*!
 * @file  MyServiceTestSVC_impl.h
 * @brief Service implementation header of MyServiceTest.idl
 *
 */

#include "MyServiceTestSkel.h"

#ifndef MYSERVICETESTSVC_IMPL_H
#define MYSERVICETESTSVC_IMPL_H
 
/*!
 * @class SimpleService_MyServiceSVC_impl
 * Example class implementing IDL interface SimpleService::MyService
 */
class SimpleService_MyServiceSVC_impl
 : public virtual POA_SimpleService::MyService,
   public virtual PortableServer::RefCountServantBase
{
 private:
   // Make sure all instances are built on the heap by making the
   // destructor non-public
   //virtual ~SimpleService_MyServiceSVC_impl();

 public:
  /*!
   * @brief standard constructor
   */
   SimpleService_MyServiceSVC_impl();
  /*!
   * @brief destructor
   */
   virtual ~SimpleService_MyServiceSVC_impl();

   // attributes and operations
   char* echo(const char* msg);
   SimpleService::EchoList* get_echo_history();
   void set_value(::CORBA::Float value);
   ::CORBA::Float get_value();
   SimpleService::ValueList* get_value_history();

};



#endif // MYSERVICETESTSVC_IMPL_H


