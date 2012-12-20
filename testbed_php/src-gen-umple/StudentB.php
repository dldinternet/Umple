<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.15.0.1751 modeling language!*/

class StudentB
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static $nextX = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Autounique Attributes
  private $x;

  //StudentB Associations
  private $program;
  public $mentor; //until PHP 5.3 (setAccessible)

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct()
  {
    $this->x = self::$nextX++;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function getX()
  {
    return $this->x;
  }

  public function getProgram()
  {
    return $this->program;
  }

  public function getMentor()
  {
    return $this->mentor;
  }

  public function setProgram($newProgram)
  {
    $wasSet = false;
    if ($newProgram == null)
    {
      $existingProgram = $this->program;
      $this->program = null;
      
      if ($existingProgram != null && $existingProgram->getStudent() != null)
      {
        $existingProgram->setStudent(null);
      }
      $wasSet = true;
      return $wasSet;
    }
    
    $currentProgram = $this->getProgram();
    if ($currentProgram != null && $currentProgram != $newProgram)
    {
      $currentProgram->setStudent(null);
    }
    
    $this->program = $newProgram;
    $existingStudent = $newProgram->getStudent();
    
    if ($this != $existingStudent)
    {
      $newProgram->setStudent($this);
    }
    $wasSet = true;
    return $wasSet;
  }

  public function setMentor($newMentor)
  {
    $wasSet = false;
    if ($this->mentor != null && $this->mentor != $newMentor && $this == $this->mentor->getStudent())
    {
      //Unable to setMentor, as existing mentor would become an orphan
      return $wasSet;
    }
    
    $this->mentor = $newMentor;
    $oldStudent = $newMentor != null ? $newMentor->getStudent() : null;
    
    if ($this != $oldStudent)
    {
      if ($oldStudent != null)
      {
        $oldStudent->mentor = null;
      }
      if ($this->mentor != null)
      {
        $this->mentor->setStudent($this);
      }
    }
    $wasSet = true;
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    if ($this->program != null)
    {
      $this->program->setStudent(null);
    }
    $existingMentor = $this->mentor;
    $this->mentor = null;
    if ($existingMentor != null)
    {
      $existingMentor->delete();
    }
  }

}
?>