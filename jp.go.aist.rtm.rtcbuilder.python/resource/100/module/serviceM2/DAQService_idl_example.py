#!/usr/bin/env python
# -*- coding: utf-8 -*-
# -*- Python -*-
"""
 @file DAQService_idl_example.py
 @brief Python example implementations generated from DAQService.idl
 @date $Date$
"""
import omniORB
from omniORB import CORBA, PortableServer
import _GlobalIDL, _GlobalIDL__POA
class DAQService_i (_GlobalIDL__POA.DAQService):
    """
    @class DAQService_i
    Example class implementing IDL interface DAQService
    """
    def __init__(self):
        """
        @brief standard constructor
        Initialise member variables here
        """
        pass
    # long setCommand(in long com, in double par)
    def setCommand(self, com, par):
        raise CORBA.NO_IMPLEMENT(0, CORBA.COMPLETED_NO)
        # *** Implement me
        # Must return: result
    # short getCommand()
    def getCommand(self):
        raise CORBA.NO_IMPLEMENT(0, CORBA.COMPLETED_NO)
        # *** Implement me
        # Must return: result
    # unsigned long checkDone()
    def checkDone(self):
        raise CORBA.NO_IMPLEMENT(0, CORBA.COMPLETED_NO)
        # *** Implement me
        # Must return: result
    # void setDone()
    def setDone(self):
        raise CORBA.NO_IMPLEMENT(0, CORBA.COMPLETED_NO)
        # *** Implement me
        # Must return: None
if __name__ == "__main__":
    import sys
    
    # Initialise the ORB
    orb = CORBA.ORB_init(sys.argv)
    
    # As an example, we activate an object in the Root POA
    poa = orb.resolve_initial_references("RootPOA")
    # Create an instance of a servant class
    servant = DAQService_i()
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
