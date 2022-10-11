public class OpenTopContainer extends Container{

    private final String [] contents = {"pipes", "construction materials", "trees", "industry machines"};

    public OpenTopContainer() {
        super();
        this.setContent(getRandomContent());
    }

    @Override
    protected String getRandomContent() {
        int randomInt = (int) (Math.random() * contents.length);
        return contents[randomInt];
    }

}
