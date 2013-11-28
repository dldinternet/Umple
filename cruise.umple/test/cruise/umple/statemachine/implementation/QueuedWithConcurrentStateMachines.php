<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.18.0.3036 modeling language!*/

class QueuedWithConcurrentStateMachines
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //QueuedWithConcurrentStateMachines State Machines
  private static $SmS1 = 1;
  private static $SmS2 = 2;
  private static $SmS2aNull = 3;
  private static $SmS2aS2a = 4;
  private static $SmS2bNull = 5;
  private static $SmS2bS2b = 6;
  private $sm;
  private $smS2a;
  private $smS2b;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct()
  {
    $this->setSmS2a(self::$SmS2aNull);
    $this->setSmS2b(self::$SmS2bNull);
    $this->setSm(self::$SmS1);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function getSmFullName()
  {
    $answer = $this->getSm();
    if ($this->smS2a != self::$SmS2aNull) { $answer .= "." . $this->getSmS2a(); }
    if ($this->smS2b != self::$SmS2bNull) { $answer .= "." . $this->getSmS2b(); }
    return $answer;
  }

  public function getSm()
  {
    if ($this->sm == self::$SmS1) { return "SmS1"; }
    elseif ($this->sm == self::$SmS2) { return "SmS2"; }
    return null;
  }

  public function getSmS2a()
  {
    if ($this->smS2a == self::$SmS2aNull) { return "SmS2aNull"; }
    elseif ($this->smS2a == self::$SmS2aS2a) { return "SmS2aS2a"; }
    return null;
  }

  public function getSmS2b()
  {
    if ($this->smS2b == self::$SmS2bNull) { return "SmS2bNull"; }
    elseif ($this->smS2b == self::$SmS2bS2b) { return "SmS2bS2b"; }
    return null;
  }

  public function e1()
  {
    $wasEventProcessed = false;
    
    $aSm = $this->sm;
    if ($aSm == self::$SmS1)
    {
      $this->setSm(self::$SmS2);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  private function enterS2()
  {
    $wasEventProcessed = false;
    
    $aSmS2a = $this->smS2a;
    $aSmS2b = $this->smS2b;
    if ($aSmS2a == self::$SmS2aNull)
    {
      $this->setSmS2a(self::$SmS2aS2a);
      $wasEventProcessed = true;
    }

    
    if ($aSmS2b == self::$SmS2bNull)
    {
      $this->setSmS2b(self::$SmS2bS2b);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  private function exitS2()
  {
    $wasEventProcessed = false;
    
    $aSmS2a = $this->smS2a;
    $aSmS2b = $this->smS2b;
    if ($aSmS2a == self::$SmS2aS2a)
    {
      $this->setSmS2a(self::$SmS2aNull);
      $wasEventProcessed = true;
    }

    
    if ($aSmS2b == self::$SmS2bS2b)
    {
      $this->setSmS2b(self::$SmS2bNull);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  public function e2()
  {
    $wasEventProcessed = false;
    
    $aSmS2a = $this->smS2a;
    $aSmS2b = $this->smS2b;
    if ($aSmS2a == self::$SmS2aS2a)
    {
      $this->setSmS2b(self::$SmS2bS2b);
      $wasEventProcessed = true;
    }

    
    if ($aSmS2b == self::$SmS2bS2b)
    {
      $this->exitSm();
      $this->setSm(self::$SmS1);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  private function exitSm()
  {
    if ($this->sm == self::$SmS2)
    {
      $this->exitS2();
    }
  }

  private function setSm($aSm)
  {
    $this->sm = $aSm;

    // entry actions and do activities
    if ($this->sm == self::$SmS2)
    {
      if ($this->smS2a == self::$SmS2aNull) { $this->setSmS2a(self::$SmS2aS2a); }
      if ($this->smS2b == self::$SmS2bNull) { $this->setSmS2b(self::$SmS2bS2b); }
    }
  }

  private function setSmS2a($aSmS2a)
  {
    $this->smS2a = $aSmS2a;
    if ($this->sm != self::$SmS2 && $aSmS2a != self::$SmS2aNull) { $this->setSm(self::$SmS2); }
  }

  private function setSmS2b($aSmS2b)
  {
    $this->smS2b = $aSmS2b;
    if ($this->sm != self::$SmS2 && $aSmS2b != self::$SmS2bNull) { $this->setSm(self::$SmS2); }
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {}

}
?>