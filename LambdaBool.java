
/**
 * used as a workaround hack to the following issue I encountered:
 * "local variables referenced from a lambda expression must be final or effectively final"
 */
public class LambdaBool
{
    // instance variables - replace the example below with your own
    private boolean proceed;

    /**
     * Constructor for objects of class LambaBool
     */
    public LambdaBool() {
        // initialise instance variables
        this.proceed = false;
    }

    public LambdaBool(boolean proceed) {
        this.proceed = proceed;
    }

    public void setProceed(boolean yesOrNo) {
        this.proceed = yesOrNo;
        System.out.println("LambdaBool proceed has been set to: " + this.getProceed());
    }
    
    public boolean getProceed() {
        return proceed;
    }
}
