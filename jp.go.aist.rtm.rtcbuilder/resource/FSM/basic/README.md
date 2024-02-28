﻿# ModuleName

## Overview

ModuleDescription

## Description



### Input and Output



### Algorithm etc



### Basic Information

|  |  |
----|---- 
| Module Name | ModuleName |
| Description | ModuleDescription |
| Version | 1.0.0 |
| Vendor | VenderName |
| Category | Category |
| Comp. Type | STATIC |
| Act. Type | PERIODIC |
| Kind | DataFlowComponent |
| MAX Inst. | 1 |

### Activity definition

<table>
  <tr>
    <td rowspan="4">on_initialize</td>
    <td colspan="2">implemented</td>
    <tr>
      <td>Description</td>
      <td></td>
    </tr>
    <tr>
      <td>PreCondition</td>
      <td></td>
    </tr>
    <tr>
      <td>PostCondition</td>
      <td></td>
    </tr>
  </tr>
  <tr>
    <td>on_finalize</td>
    <td colspan="2"></td>
  </tr>
  <tr>
    <td>on_startup</td>
    <td colspan="2"></td>
  </tr>
  <tr>
    <td>on_shutdown</td>
    <td colspan="2"></td>
  </tr>
  <tr>
    <td>on_activated</td>
    <td colspan="2"></td>
  </tr>
  <tr>
    <td>on_deactivated</td>
    <td colspan="2"></td>
  </tr>
  <tr>
    <td>on_execute</td>
    <td colspan="2"></td>
  </tr>
  <tr>
    <td>on_aborting</td>
    <td colspan="2"></td>
  </tr>
  <tr>
    <td>on_error</td>
    <td colspan="2"></td>
  </tr>
  <tr>
    <td>on_reset</td>
    <td colspan="2"></td>
  </tr>
  <tr>
    <td>on_state_update</td>
    <td colspan="2"></td>
  </tr>
  <tr>
    <td>on_rate_changed</td>
    <td colspan="2"></td>
  </tr>
</table>

### EventPorts definition

|  |  |
----|---- 
| Port Name | FSMEvent |
| FSM Type | StaticFSM |

#### Node list

<table>
  <tr>
    <td>State Name</td>
    <td>Event Name</td>
    <td>Target State</td>
  </tr>
  <tr>
    <td>InitialState</td>
    <td colspan="2">Initial State</td>
  </tr>
  <tr>
    <td rowspan="1">State01</td>
    <td></td>
    <td>State02</td>
  </tr>
  <tr>
    <td rowspan="1">State02</td>
    <td></td>
    <td>FinalState</td>
  </tr>
  <tr>
    <td>FinalState</td>
    <td colspan="2">Final State</td>
  </tr>

</table>

#### Event list

##### 



<table>
  <tr>
    <td>Source State</td>
    <td colspan="2">InitialState</td>
  </tr>
  <tr>
    <td>Target State</td>
    <td colspan="2">State01</td>
  </tr>
  <tr>
    <td>DataType</td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td>Number of Data</td>
    <td colspan="2"></td>
  </tr>
  <tr>
    <td>Unit</td>
    <td colspan="2"></td>
  </tr>
  <tr>
    <td>Operational frecency Period</td>
    <td colspan="2"></td>
  </tr>
</table>



##### 



<table>
  <tr>
    <td>Source State</td>
    <td colspan="2">State01</td>
  </tr>
  <tr>
    <td>Target State</td>
    <td colspan="2">State02</td>
  </tr>
  <tr>
    <td>DataType</td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td>Number of Data</td>
    <td colspan="2"></td>
  </tr>
  <tr>
    <td>Unit</td>
    <td colspan="2"></td>
  </tr>
  <tr>
    <td>Operational frecency Period</td>
    <td colspan="2"></td>
  </tr>
</table>



##### 



<table>
  <tr>
    <td>Source State</td>
    <td colspan="2">State02</td>
  </tr>
  <tr>
    <td>Target State</td>
    <td colspan="2">FinalState</td>
  </tr>
  <tr>
    <td>DataType</td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td>Number of Data</td>
    <td colspan="2"></td>
  </tr>
  <tr>
    <td>Unit</td>
    <td colspan="2"></td>
  </tr>
  <tr>
    <td>Operational frecency Period</td>
    <td colspan="2"></td>
  </tr>
</table>





### InPorts definition


### OutPorts definition


### Service Port definition


### Configuration definition


## Demo

## Requirement

## Setup

### Windows

### Ubuntu

## Usage

## Running the tests

## LICENCE




## References




## Author


