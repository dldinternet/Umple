<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.15.0.1751 modeling language!*/

class MentorV
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MentorV Attributes
  private $name;

  //MentorV Associations
  private $students;
  private $program;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName)
  {
    $this->name = $aName;
    $this->students = array();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setName($aName)
  {
    $wasSet = false;
    $this->name = $aName;
    $wasSet = true;
    return $wasSet;
  }

  public function getName()
  {
    return $this->name;
  }

  public function getStudent($index)
  {
    $aStudent = $this->students[$index];
    return $aStudent;
  }

  public function getStudents()
  {
    $newStudents = $this->students;
    return $newStudents;
  }

  public function numberOfStudents()
  {
    $number = count($this->students);
    return $number;
  }

  public function hasStudents()
  {
    $has = $this->numberOfStudents() > 0;
    return $has;
  }

  public function indexOfStudent($aStudent)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->students as $student)
    {
      if ($student->equals($aStudent))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getProgram()
  {
    return $this->program;
  }

  public function isNumberOfStudentsValid()
  {
    $isValid = $this->numberOfStudents() >= self::minimumNumberOfStudents() && $this->numberOfStudents() <= self::maximumNumberOfStudents();
    return $isValid;
  }

  public static function requiredNumberOfStudents()
  {
    return 3;
  }

  public static function minimumNumberOfStudents()
  {
    return 3;
  }

  public static function maximumNumberOfStudents()
  {
    return 3;
  }

  public function addStudent($aStudent)
  {
    $wasAdded = false;
    if ($this->indexOfStudent($aStudent) !== -1) { return false; }
    if ($this->numberOfStudents() >= self::maximumNumberOfStudents())
    {
      return $wasAdded;
    }

    $this->students[] = $aStudent;
    if ($aStudent->indexOfMentor($this) != -1)
    {
      $wasAdded = true;
    }
    else
    {
      $wasAdded = $aStudent->addMentor($this);
      if (!$wasAdded)
      {
        array_pop($this->students);
      }
    }
    return $wasAdded;
  }

  public function removeStudent($aStudent)
  {
    $wasRemoved = false;
    if ($this->indexOfStudent($aStudent) == -1)
    {
      return $wasRemoved;
    }

    if ($this->numberOfStudents() <= self::minimumNumberOfStudents())
    {
      return $wasRemoved;
    }

    $oldIndex = $this->indexOfStudent($aStudent);
    unset($this->students[$oldIndex]);
    if ($aStudent->indexOfMentor($this) == -1)
    {
      $wasRemoved = true;
    }
    else
    {
      $wasRemoved = $aStudent->removeMentor($this);
      if (!$wasRemoved)
      {
        $this->students[$oldIndex] = $aStudent;
        ksort($this->students);
      }
    }
    $this->students = array_values($this->students);
    return $wasRemoved;
  }

  public function setStudents($newStudents)
  {
    $wasSet = false;
    $verifiedStudents = array();
    foreach ($newStudents as $aStudent)
    {
      if (array_search($aStudent,$verifiedStudents) !== false)
      {
        continue;
      }
      $verifiedStudents[] = $aStudent;
    }

    if (count($verifiedStudents) != count($newStudents) || count($verifiedStudents) < self::minimumNumberOfStudents() || count($verifiedStudents) > self::maximumNumberOfStudents())
    {
      return $wasSet;
    }

    $oldStudents = $this->students;
    $this->students = array();
    foreach ($verifiedStudents as $newStudent)
    {
      $this->students[] = $newStudent;
      $removeIndex = array_search($newStudent,$oldStudents);
      if ($removeIndex !== false)
      {
        unset($oldStudents[$removeIndex]);
        $oldStudents = array_values($oldStudents);
      }
      else
      {
        $newStudent->addMentor($this);
      }
    }

    foreach ($oldStudents as $oldStudent)
    {
      $oldStudent->removeMentor($this);
    }
    $wasSet = true;
    return $wasSet;
  }

  public function setProgram($newProgram)
  {
    $wasSet = false;
    if ($newProgram == null)
    {
      $existingProgram = $this->program;
      $this->program = null;
      
      if ($existingProgram != null && $existingProgram->getMentor() != null)
      {
        $existingProgram->setMentor(null);
      }
      $wasSet = true;
      return $wasSet;
    }
    
    $currentProgram = $this->getProgram();
    if ($currentProgram != null && $currentProgram != $newProgram)
    {
      $currentProgram->setMentor(null);
    }
    
    $this->program = $newProgram;
    $existingMentor = $newProgram->getMentor();
    
    if ($this != $existingMentor)
    {
      $newProgram->setMentor($this);
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
    $copyOfStudents = $this->students;
    $this->students = array();
    foreach ($copyOfStudents as $aStudent)
    {
      if ($aStudent->numberOfMentors() <= StudentV::minimumNumberOfMentors())
      {
        $aStudent->delete();
      }
      else
      {
        $aStudent->removeMentor($this);
      }
    }
    if ($this->program != null)
    {
      $this->program->setMentor(null);
    }
  }

}
?>