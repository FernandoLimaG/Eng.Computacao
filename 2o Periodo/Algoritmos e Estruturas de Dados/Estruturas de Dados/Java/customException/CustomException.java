package customException;

public class CustomException
{
    public static class Overflow extends RuntimeException
    {
        public Overflow(String s){ super(s); }
        public Overflow(){ super("Overflow"); }
    }

    public static class Underflow extends RuntimeException
    {
        public Underflow(String s){ super(s); }
        public Underflow(){ super("Underflow"); }
    }
}
