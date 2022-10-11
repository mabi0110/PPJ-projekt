public class TankContainer extends Container {

    private final String [] contents = {"milk", "water", "oil", "juice", "chemicals"};

    public TankContainer() {
        super();
        this.setContent(getRandomContent());
    }

    @Override
    protected String getRandomContent() {
        int randomInt = (int) (Math.random() * contents.length);
        return contents[randomInt];
    }

}
