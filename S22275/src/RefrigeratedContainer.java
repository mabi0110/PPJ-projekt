public class RefrigeratedContainer extends Container {

    private final String [] contents = {"fishes", "vegetables", "meat", "fruits", "butter"};

    public RefrigeratedContainer() {
        super();
        this.setContent(getRandomContent());
    }

    @Override
    protected String getRandomContent() {
        int randomInt = (int) (Math.random() * contents.length);
        return contents[randomInt];
    }


}
