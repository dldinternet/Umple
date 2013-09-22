<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE ${last.version} modeling language!*/

class CourseF
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //CourseF State Machines
  private static $StatusOff = 1;
  private static $StatusOn = 2;
  private static $StatusMotorIdleNull = 3;
  private static $StatusMotorIdleMotorIdle = 4;
  private static $StatusMotorIdleMotorRunning = 5;
  private static $StatusFanIdleNull = 6;
  private static $StatusFanIdleFanIdle = 7;
  private static $StatusFanIdleFanRunning = 8;
  private $status;
  private $statusMotorIdle;
  private $statusFanIdle;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct()
  {
    $this->setStatusMotorIdle(self::$StatusMotorIdleNull);
    $this->setStatusFanIdle(self::$StatusFanIdleNull);
    $this->setStatus(self::$StatusOff);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function getStatusFullName()
  {
    $answer = $this->getStatus();
    if ($this->statusMotorIdle != self::$StatusMotorIdleNull) { $answer .= "." . $this->getStatusMotorIdle(); }
    if ($this->statusFanIdle != self::$StatusFanIdleNull) { $answer .= "." . $this->getStatusFanIdle(); }
    return $answer;
  }

  public function getStatus()
  {
    if ($this->status == self::$StatusOff) { return "StatusOff"; }
    elseif ($this->status == self::$StatusOn) { return "StatusOn"; }
    return null;
  }

  public function getStatusMotorIdle()
  {
    if ($this->statusMotorIdle == self::$StatusMotorIdleNull) { return "StatusMotorIdleNull"; }
    elseif ($this->statusMotorIdle == self::$StatusMotorIdleMotorIdle) { return "StatusMotorIdleMotorIdle"; }
    elseif ($this->statusMotorIdle == self::$StatusMotorIdleMotorRunning) { return "StatusMotorIdleMotorRunning"; }
    return null;
  }

  public function getStatusFanIdle()
  {
    if ($this->statusFanIdle == self::$StatusFanIdleNull) { return "StatusFanIdleNull"; }
    elseif ($this->statusFanIdle == self::$StatusFanIdleFanIdle) { return "StatusFanIdleFanIdle"; }
    elseif ($this->statusFanIdle == self::$StatusFanIdleFanRunning) { return "StatusFanIdleFanRunning"; }
    return null;
  }

  public function turnOn()
  {
    $wasEventProcessed = false;
    
    $aStatus = $this->status;
    if ($aStatus == self::$StatusOff)
    {
      $this->setStatus(self::$StatusOn);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  public function turnOff()
  {
    $wasEventProcessed = false;
    
    $aStatus = $this->status;
    if ($aStatus == self::$StatusOn)
    {
      $this->exitStatus();
      $this->setStatus(self::$StatusOff);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  private function enterOn()
  {
    $wasEventProcessed = false;
    
    $aStatusMotorIdle = $this->statusMotorIdle;
    $aStatusFanIdle = $this->statusFanIdle;
    if ($aStatusMotorIdle == self::$StatusMotorIdleNull)
    {
      $this->setStatusMotorIdle(self::$StatusMotorIdleMotorIdle);
      $wasEventProcessed = true;
    }

    
    if ($aStatusFanIdle == self::$StatusFanIdleNull)
    {
      $this->setStatusFanIdle(self::$StatusFanIdleFanIdle);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  private function exitOn()
  {
    $wasEventProcessed = false;
    
    $aStatusMotorIdle = $this->statusMotorIdle;
    $aStatusFanIdle = $this->statusFanIdle;
    if ($aStatusMotorIdle == self::$StatusMotorIdleMotorIdle)
    {
      $this->setStatusMotorIdle(self::$StatusMotorIdleNull);
      $wasEventProcessed = true;
    }
    elseif ($aStatusMotorIdle == self::$StatusMotorIdleMotorRunning)
    {
      $this->setStatusMotorIdle(self::$StatusMotorIdleNull);
      $wasEventProcessed = true;
    }

    
    if ($aStatusFanIdle == self::$StatusFanIdleFanIdle)
    {
      $this->setStatusFanIdle(self::$StatusFanIdleNull);
      $wasEventProcessed = true;
    }
    elseif ($aStatusFanIdle == self::$StatusFanIdleFanRunning)
    {
      $this->setStatusFanIdle(self::$StatusFanIdleNull);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  public function flip()
  {
    $wasEventProcessed = false;
    
    $aStatusMotorIdle = $this->statusMotorIdle;
    if ($aStatusMotorIdle == self::$StatusMotorIdleMotorIdle)
    {
      $this->setStatusMotorIdle(self::$StatusMotorIdleMotorRunning);
      $wasEventProcessed = true;
    }
    elseif ($aStatusMotorIdle == self::$StatusMotorIdleMotorRunning)
    {
      $this->setStatusMotorIdle(self::$StatusMotorIdleMotorIdle);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  public function flup()
  {
    $wasEventProcessed = false;
    
    $aStatusMotorIdle = $this->statusMotorIdle;
    if ($aStatusMotorIdle == self::$StatusMotorIdleMotorIdle)
    {
      $this->exitStatus();
      $this->setStatus(self::$StatusOff);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  public function flop()
  {
    $wasEventProcessed = false;
    
    $aStatusFanIdle = $this->statusFanIdle;
    if ($aStatusFanIdle == self::$StatusFanIdleFanIdle)
    {
      $this->setStatusFanIdle(self::$StatusFanIdleFanRunning);
      $wasEventProcessed = true;
    }
    elseif ($aStatusFanIdle == self::$StatusFanIdleFanRunning)
    {
      $this->setStatusFanIdle(self::$StatusFanIdleFanIdle);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  private function exitStatus()
  {
    if ($this->status == self::$StatusOn)
    {
      $this->exitOn();
    }
  }

  private function setStatus($aStatus)
  {
    $this->status = $aStatus;

    // entry actions and do activities
    if ($this->status == self::$StatusOn)
    {
      if ($this->statusMotorIdle == self::$StatusMotorIdleNull) { $this->setStatusMotorIdle(self::$StatusMotorIdleMotorIdle); }
      if ($this->statusFanIdle == self::$StatusFanIdleNull) { $this->setStatusFanIdle(self::$StatusFanIdleFanIdle); }
    }
  }

  private function setStatusMotorIdle($aStatusMotorIdle)
  {
    $this->statusMotorIdle = $aStatusMotorIdle;
    if ($this->status != self::$StatusOn && $aStatusMotorIdle != self::$StatusMotorIdleNull) { $this->setStatus(self::$StatusOn); }
  }

  private function setStatusFanIdle($aStatusFanIdle)
  {
    $this->statusFanIdle = $aStatusFanIdle;
    if ($this->status != self::$StatusOn && $aStatusFanIdle != self::$StatusFanIdleNull) { $this->setStatus(self::$StatusOn); }
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {}

}
?>