package it.unibo.mvc;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.controller.DrawNumberControllerImpl;
import it.unibo.mvc.model.DrawNumberImpl;

import java.lang.reflect.InvocationTargetException;

/**
 * Application entry-point.
 */
public final class LaunchApp {

    private LaunchApp() { }

    /**
     * Runs the application.
     *
     * @param args ignored
     * @throws ClassNotFoundException if the fetches class does not exist
     * @throws NoSuchMethodException if the 0-ary constructor do not exist
     * @throws InvocationTargetException if the constructor throws exceptions
     * @throws InstantiationException if the constructor throws exceptions
     * @throws IllegalAccessException in case of reflection issues
     * @throws IllegalArgumentException in case of reflection issues
     */
    public static void main(final String... args){
        final var model = new DrawNumberImpl();
        final DrawNumberController app = new DrawNumberControllerImpl(model);
        try {
            final Class<?> clazz1 = Class.forName("it.unibo.mvc.view.DrawNumberSwingView");
            final Class<?> clazz2 = Class.forName("it.unibo.mvc.view.DrawNumberStandardOutputView");
            for(int i = 0; i < 3; i++){
                final Object clazz1View = clazz1.getConstructor().newInstance();
                final Object clazz2View = clazz2.getConstructor().newInstance();
                app.addView((DrawNumberView)clazz1View);
                app.addView((DrawNumberView)clazz2View);

            }
        } catch (ClassNotFoundException | 
        InstantiationException | 
        IllegalAccessException | 
        IllegalArgumentException | 
        InvocationTargetException | 
        NoSuchMethodException |
        IllegalStateException | 
        SecurityException e) {
            e.printStackTrace();
        }

    }
}
