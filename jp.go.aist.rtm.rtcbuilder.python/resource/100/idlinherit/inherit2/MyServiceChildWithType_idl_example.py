#!/usr/bin/env python
# -*- coding: utf-8 -*-
# -*- Python -*-
"""
 @file MyServiceChildWithType_idl_example.py
 @brief Python example implementations generated from MyServiceChildWithType.idl
 @date $Date$
"""
import omniORB
from omniORB import CORBA, PortableServer
import _GlobalIDL, _GlobalIDL__POA
class MyServiceWithType_i (_GlobalIDL__POA.MyServiceWithType):
    """
    @class MyServiceWithType_i
    Example class implementing IDL interface MyServiceWithType
    """
    def __init__(self):
        """
        @brief standard constructor
        Initialise member variables here
        """
        pass
    # EchoList get_echo_history()
    def get_echo_history(self):
        raise CORBA.NO_IMPLEMENT(0, CORBA.COMPLETED_NO)
        # *** Implement me
        # Must return: result
    # ValueList get_value_history()
    def get_value_history(self):
        raise CORBA.NO_IMPLEMENT(0, CORBA.COMPLETED_NO)
        # *** Implement me
        # Must return: result
class MyServiceWithTypeChild_i (_GlobalIDL__POA.MyServiceWithTypeChild):
    """
    @class MyServiceWithTypeChild_i
    Example class implementing IDL interface MyServiceWithTypeChild
    """
    def __init__(self):
        """
        @brief standard constructor
        Initialise member variables here
        """
        pass
    # void setPos(in float pos)
    def setPos(self, pos):
        raise CORBA.NO_IMPLEMENT(0, CORBA.COMPLETED_NO)
        # *** Implement me
        # Must return: None
    # EchoList getPos()
    def getPos(self):
        raise CORBA.NO_IMPLEMENT(0, CORBA.COMPLETED_NO)
        # *** Implement me
        # Must return: result
    # EchoList get_echo_history()
    def get_echo_history(self):
        raise CORBA.NO_IMPLEMENT(0, CORBA.COMPLETED_NO)
        # *** Implement me
        # Must return: result
    # ValueList get_value_history()
    def get_value_history(self):
        raise CORBA.NO_IMPLEMENT(0, CORBA.COMPLETED_NO)
        # *** Implement me
        # Must return: result
if __name__ == "__main__":
    import sys
    
    # Initialise the ORB
    orb = CORBA.ORB_init(sys.argv)
    
    # As an example, we activate an object in the Root POA
    poa = orb.resolve_initial_references("RootPOA")
    # Create an instance of a servant class
    servant = MyServiceWithType_i()
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
