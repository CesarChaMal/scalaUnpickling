import java.io._
import scala.util.Try

object UnpicklingExamples {

  // Example 1: Unpickling (Deserializing) a Simple Object
  def simpleUnpicklingExample(): Try[Person] = {
    println("Running: simpleUnpicklingExample - This example demonstrates basic unpickling of a serialized Person object.")

    Try {
      val fileInputStream = new FileInputStream("person.ser")
      val objectInputStream = new ObjectInputStream(fileInputStream)

      val person = objectInputStream.readObject().asInstanceOf[Person]

      objectInputStream.close()
      fileInputStream.close()

      println(s"Successfully unpickled Person: $person")
      person
    }.recover {
      case e: Exception =>
        println(s"Error during unpickling: ${e.getMessage}")
        throw e
    }
  }

  // Example 2: Unpickling a List of Objects
  def listUnpicklingExample(): Try[List[Person]] = {
    println("Running: listUnpicklingExample - This example demonstrates unpickling a list of Person objects.")

    Try {
      val fileInputStream = new FileInputStream("people.ser")
      val objectInputStream = new ObjectInputStream(fileInputStream)

      val people = objectInputStream.readObject().asInstanceOf[List[Person]]

      objectInputStream.close()
      fileInputStream.close()

      println(s"Successfully unpickled List of People: $people")
      people
    }.recover {
      case e: Exception =>
        println(s"Error during unpickling: ${e.getMessage}")
        throw e
    }
  }

  // Example 3: Unpickling with Custom ClassLoader
  def unpicklingWithCustomClassLoader(): Try[Any] = {
    println("Running: unpicklingWithCustomClassLoader - This example demonstrates unpickling using a custom ClassLoader.")

    Try {
      val fileInputStream = new FileInputStream("customObject.ser")
      val objectInputStream = new ObjectInputStream(fileInputStream) {
        override def resolveClass(desc: ObjectStreamClass): Class[_] = {
          println(s"Resolving class: ${desc.getName}")
          super.resolveClass(desc)
        }
      }

      val customObject = objectInputStream.readObject()

      objectInputStream.close()
      fileInputStream.close()

      println(s"Successfully unpickled Custom Object: $customObject")
      customObject
    }.recover {
      case e: Exception =>
        println(s"Error during unpickling with custom class loader: ${e.getMessage}")
        throw e
    }
  }

  // Example 4: Handling Legacy Serialized Data
  def unpicklingLegacyData(): Try[Any] = {
    println("Running: unpicklingLegacyData - This example demonstrates unpickling legacy serialized data.")

    Try {
      val fileInputStream = new FileInputStream("legacyData.ser")
      val objectInputStream = new ObjectInputStream(fileInputStream)

      val legacyData = objectInputStream.readObject()

      objectInputStream.close()
      fileInputStream.close()

      println(s"Successfully unpickled Legacy Data: $legacyData")
      legacyData
    }.recover {
      case e: Exception =>
        println(s"Error during unpickling legacy data: ${e.getMessage}")
        throw e
    }
  }

  // Example 5: Unpickling into a Different Version of a Class
  def unpicklingWithClassChanges(): Try[Person] = {
    println("Running: unpicklingWithClassChanges - This example demonstrates unpickling into a different version of a class.")

    Try {
      val fileInputStream = new FileInputStream("updatedPerson.ser")
      val objectInputStream = new ObjectInputStream(fileInputStream)

      val person = objectInputStream.readObject().asInstanceOf[Person]

      objectInputStream.close()
      fileInputStream.close()

      println(s"Successfully unpickled Updated Person: $person")
      person
    }.recover {
      case e: Exception =>
        println(s"Error during unpickling with class changes: ${e.getMessage}")
        throw e
    }
  }

  // Main function to run all examples
  def main(args: Array[String]): Unit = {
    println("Starting Unpickling Examples...")

    simpleUnpicklingExample()
    listUnpicklingExample()
    unpicklingWithCustomClassLoader()
    unpicklingLegacyData()
    unpicklingWithClassChanges()

    println("Completed all Unpickling Examples.")
  }
}
