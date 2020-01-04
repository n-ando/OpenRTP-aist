# ModuleName

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
| Port Name | FSMEventPort |
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
    <td>Event01-02</td>
    <td>State02</td>
  </tr>
  <tr>
    <td rowspan="1">State02</td>
    <td>Event02-Final</td>
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



##### Event01-02

Abst01-02

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
    <td>RTC::TimedLong</td>
    <td>Type01-02</td>
  </tr>
  <tr>
    <td>Number of Data</td>
    <td colspan="2">Num01-02</td>
  </tr>
  <tr>
    <td>Unit</td>
    <td colspan="2">Unit01-02</td>
  </tr>
  <tr>
    <td>Operational frecency Period</td>
    <td colspan="2">Period01-02</td>
  </tr>
</table>

Detail01-02

##### Event02-Final

Abst02-Final

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
    <td>RTC::TimedString</td>
    <td>Type02-Final</td>
  </tr>
  <tr>
    <td>Number of Data</td>
    <td colspan="2">Num02-Final</td>
  </tr>
  <tr>
    <td>Unit</td>
    <td colspan="2">Unit02-Final</td>
  </tr>
  <tr>
    <td>Operational frecency Period</td>
    <td colspan="2">Period02-Final</td>
  </tr>
</table>

Detail02-Final



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


