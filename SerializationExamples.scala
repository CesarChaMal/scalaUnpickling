import java.io._

object SerializationExamples {
  
  // Serialize a single Person object to person.ser
  def serializePerson(): Unit = {
    val person = Person("John Doe", 30)
    val fileOutputStream = new FileOutputStream("person.ser")
    val objectOutputStream = new ObjectOutputStream(fileOutputStream)

    objectOutputStream.writeObject(person)
    objectOutputStream.close()
    fileOutputStream.close()

    println(s"Serialized Person object to person.ser: $person")
  }

  // Serialize a list of Person objects to people.ser
  def serializePeople(): Unit = {
    val people = List(Person("John Doe", 30), Person("Jane Doe", 25))
    val fileOutputStream = new FileOutputStream("people.ser")
    val objectOutputStream = new ObjectOutputStream(fileOutputStream)

    objectOutputStream.writeObject(people)
    objectOutputStream.close()
    fileOutputStream.close()

    println(s"Serialized List of People to people.ser: $people")
  }

  // Serialize a single CustomPerson object to custom_person.ser
  def serializeCustomPerson(): Unit = {
    val person = CustomPerson("Alice", 28)
    val fileOutputStream = new FileOutputStream("custom_person.ser")
    val objectOutputStream = new ObjectOutputStream(fileOutputStream)

    objectOutputStream.writeObject(person)
    objectOutputStream.close()
    fileOutputStream.close()

    println(s"Serialized CustomPerson object to custom_person.ser: $person")
  }

  // Serialize a list of CustomPerson objects to custom_people.ser
  def serializeCustomPeople(): Unit = {
    val people = List(CustomPerson("Alice", 28), CustomPerson("Bob", 35))
    val fileOutputStream = new FileOutputStream("custom_people.ser")
    val objectOutputStream = new ObjectOutputStream(fileOutputStream)

    objectOutputStream.writeObject(people)
    objectOutputStream.close()
    fileOutputStream.close()

    println(s"Serialized List of CustomPeople to custom_people.ser: $people")
  }

  // Serialize a generic custom object to customObject.ser
  def serializeCustomObject(): Unit = {
    val customObject = Map("key1" -> "value1", "key2" -> 42)
    val fileOutputStream = new FileOutputStream("customObject.ser")
    val objectOutputStream = new ObjectOutputStream(fileOutputStream)

    objectOutputStream.writeObject(customObject)
    objectOutputStream.close()
    fileOutputStream.close()

    println(s"Serialized Custom Object (Map) to customObject.ser: $customObject")
  }

  // Serialize legacy data (string) to legacyData.ser
  def serializeLegacyData(): Unit = {
    val legacyData = "This is legacy data"
    val fileOutputStream = new FileOutputStream("legacyData.ser")
    val objectOutputStream = new ObjectOutputStream(fileOutputStream)

    objectOutputStream.writeObject(legacyData)
    objectOutputStream.close()
    fileOutputStream.close()

    println(s"Serialized legacy data to legacyData.ser: $legacyData")
  }

  // Serialize custom legacy data (string) to custom_legacyData.ser
  def serializeCustomLegacyData(): Unit = {
    val customLegacyData = "This is custom legacy data"
    val fileOutputStream = new FileOutputStream("custom_legacyData.ser")
    val objectOutputStream = new ObjectOutputStream(fileOutputStream)

    objectOutputStream.writeObject(customLegacyData)
    objectOutputStream.close()
    fileOutputStream.close()

    println(s"Serialized custom legacy data to custom_legacyData.ser: $customLegacyData")
  }

  // Serialize an updated Person object to updatedPerson.ser
  def serializeUpdatedPerson(): Unit = {
    val updatedPerson = Person("Updated John Doe", 40)
    val fileOutputStream = new FileOutputStream("updatedPerson.ser")
    val objectOutputStream = new ObjectOutputStream(fileOutputStream)

    objectOutputStream.writeObject(updatedPerson)
    objectOutputStream.close()
    fileOutputStream.close()

    println(s"Serialized Updated Person object to updatedPerson.ser: $updatedPerson")
  }

  // Serialize a custom updated person for custom_updatedPerson.ser
  def serializeCustomUpdatedPerson(): Unit = {
    val updatedPerson = CustomPerson("Updated Alice", 38)
    val fileOutputStream = new FileOutputStream("custom_updatedPerson.ser")
    val objectOutputStream = new ObjectOutputStream(fileOutputStream)

    objectOutputStream.writeObject(updatedPerson)
    objectOutputStream.close()
    fileOutputStream.close()

    println(s"Serialized Custom Updated Person object to custom_updatedPerson.ser: $updatedPerson")
  }

  // Main method to run all serialization examples
  def main(args: Array[String]): Unit = {
    println("Starting Serialization Examples...")

    serializePerson()
    serializePeople()
    serializeCustomPerson()
    serializeCustomPeople()
    serializeCustomObject()
    serializeLegacyData()
    serializeUpdatedPerson()
    serializeCustomUpdatedPerson()
    serializeCustomLegacyData()
	
    println("Completed all Serialization Examples.")
  }
}
