import java.nio.ByteBuffer

// Singleton object in Scala (like a static class in other languages).
// This object is the entry point for unpickling operations.
object Unpickle {
  // The apply method is a special method in Scala that is called when you use the object like a function.
  // For example, `Unpickle[Int]` would call this method.
  def apply[A](implicit unpickler: Unpickler[A]): UnpickledCurry[A] = UnpickledCurry(unpickler)
  // `implicit` means the value is automatically provided by the compiler if available in the context.
  // `A` is a type parameter, which means this method can work for any type.
  // The method returns an instance of UnpickledCurry that contains the logic for unpickling the data.
}

// A case class in Scala is a simple class with some built-in features like immutability and pattern matching.
// Here, UnpickledCurry holds the unpickler instance and provides the fromBytes method for unpickling.
case class UnpickledCurry[A](unpickler: Unpickler[A]) {
  // The fromBytes method takes a ByteBuffer and calls the unpickler to get the data out of it.
  def fromBytes(buffer: ByteBuffer): A = unpickler.unpickle(buffer)
  // The unpickler uses the buffer to read and convert the data back into its original form (type A).
}

// Traits in Scala are similar to interfaces in Java or abstract classes in other languages.
// They define behavior that can be mixed into other classes or objects.
trait Unpickler[A] {
  // The abstract method that all unpicklers must implement. It defines how to unpickle data from a ByteBuffer.
  def unpickle(buffer: ByteBuffer): A
}

// The companion object is an object with the same name as a class or trait.
// It's often used to store related utilities, like implicit conversions or factory methods.
object Unpickler {
  // Implicit object for unpickling Boolean values.
  implicit object BooleanUnpickler extends Unpickler[Boolean] {
    override def unpickle(buffer: ByteBuffer): Boolean = buffer.get() != 0
    // This reads a single byte from the buffer. If it's not 0, it returns true; otherwise, false.
  }

  // Implicit object for unpickling Int values.
  implicit object IntUnpickler extends Unpickler[Int] {
    override def unpickle(buffer: ByteBuffer): Int = buffer.getInt()
    // This reads an integer (4 bytes) from the buffer.
  }

  // Implicit object for unpickling Long values.
  implicit object LongUnpickler extends Unpickler[Long] {
    override def unpickle(buffer: ByteBuffer): Long = buffer.getLong()
    // This reads a long (8 bytes) from the buffer.
  }

  // Implicit object for unpickling Double values.
  implicit object DoubleUnpickler extends Unpickler[Double] {
    override def unpickle(buffer: ByteBuffer): Double = buffer.getDouble()
    // This reads a double (8 bytes) from the buffer.
  }

  // Unpickler for strings. Strings require special handling because they are sequences of characters.
  implicit object StringUnpickler extends Unpickler[String] {
    override def unpickle(buffer: ByteBuffer): String = {
      val length = buffer.getInt() // First, read the length of the string (stored as an integer).
      val byteArray = new Array[Byte](length) // Create a byte array of that length.
      buffer.get(byteArray) // Fill the byte array with data from the buffer.
      new String(byteArray, "UTF-8") // Convert the byte array into a string using UTF-8 encoding.
    }
  }

  // Unpickler for sequences (like List, Vector, etc.). 
  // This method uses implicit resolution to unpickle each element in the sequence.
  implicit def seqUnpickler[A](implicit unpickler: Unpickler[A]): Unpickler[Seq[A]] = new Unpickler[Seq[A]] {
    override def unpickle(buffer: ByteBuffer): Seq[A] = {
      val length = buffer.getInt() // Get the number of elements in the sequence.
      (0 until length).map(_ => unpickler.unpickle(buffer)) // Unpickle each element using the unpickler for type A.
    }
  }
}

// A final class cannot be extended. It's a common practice to use final for classes that are not intended for inheritance.
final class UnpickleState {
  // Arrays to store references to immutable and mutable objects.
  // This helps track previously unpickled objects, which is useful in complex data structures with shared references.
  val immutableRefs: Array[Any] = Array.empty
  val identityRefs: Array[Any] = Array.empty
}

// A sample application demonstrating how to use the unpickling library.
object Example extends App {
  // The main entry point for a Scala application. Extending `App` automatically provides a main method.
  
  // Create a ByteBuffer and add some data to it.
  val buffer: ByteBuffer = ByteBuffer.allocate(1024) // Allocate 1 KB of space.
  buffer.putInt(2) // First, put the number of elements (2 in this case).
  buffer.putInt(42) // Add the first element.
  buffer.putInt(99) // Add the second element.
  buffer.flip() // Flip the buffer to prepare it for reading.

  // Use the Unpickle object to unpickle a sequence of integers.
  // The compiler automatically finds the implicit Unpickler for Seq[Int].
  val result: Seq[Int] = Unpickle[Seq[Int]].fromBytes(buffer)

  // Print the result: Seq(42, 99)
  println(result)
}
