package it.unibo.mvc.view;
import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawResult;

public class DrawNumberStandardOutputView implements DrawNumberView {

    @Override
    public void setController(DrawNumberController observer) {
        //this UI is output only
    }

    @Override
    public void start() {
        //just console
    }

    @Override
    public void result(DrawResult res) {
        System.out.println(res.getDescription());
    }

}
