// -*-C++-*-
/*!
 * @file  MyServiceSVC_impl.cpp
 * @brief Service implementation code of MyService.idl
 *
 * @author Noriaki Ando <n-ando@aist.go.jp> one two three four
 * five six seven eight nine ten
 *
 * Copyright (C) 2006-2008
 * ライセンス12345678901234567890123456789012345678901234567890
 *
 */

#include "MyServiceSVC_impl.h"

/*
 * Example implementational code for IDL interface MyService
 */
MyServiceSVC_impl::MyServiceSVC_impl()
{
  // Please add extra constructor code here.
}


MyServiceSVC_impl::~MyServiceSVC_impl()
{
  // Please add extra destructor code here.
}


/*
 * Methods corresponding to IDL attributes and operations
 */
void MyServiceSVC_impl::setGain(::CORBA::Float gain)
{
  // Please insert your code here and remove the following warning pragma
#ifndef WIN32
  #warning "Code missing in function <void MyServiceSVC_impl::setGain(::CORBA::Float gain)>"
#endif
}

::CORBA::Float MyServiceSVC_impl::getGain()
{
  // Please insert your code here and remove the following warning pragma
#ifndef WIN32
  #warning "Code missing in function <::CORBA::Float MyServiceSVC_impl::getGain()>"
#endif
  return 0;
}



// End of example implementational code



