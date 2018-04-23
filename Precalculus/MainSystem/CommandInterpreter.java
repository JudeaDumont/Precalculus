package MainSystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class CommandInterpreter implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        parseAndExecuteCommand(e);
    }

    private void parseAndExecuteCommand(ActionEvent e) {
        String[] splitCommand = e.getActionCommand().split(" ");
        String commandName = splitCommand[0]; //Create, Read, Update, Delete
        String commandArg1 = null;
        if (splitCommand.length > 1) {
            commandArg1 = splitCommand[1];
        }
//        System.out.println(commandName);
        switch (commandName) {
            case "Clear": //test
                DisplayHelper.getDisplay().cartesianCoordinateSystemsDisplays.iterator().forEachRemaining(new Consumer<CartesianCoordinateSystemDisplay>() {
                    @Override
                    public void accept(CartesianCoordinateSystemDisplay cartesianCoordinateSystemDisplay) {
                        cartesianCoordinateSystemDisplay.clear(false);
                        DisplayHelper.getDisplay().repaint();
                    }
                });
                break;
            case "CT": //test
                for (int i = 0; i < 100; i++) {
                    String[] args = new String[4];
                    args[0] = "0";
                    args[1] = "0";
                    args[2] = Integer.toString(i * i);
                    args[3] = Integer.toString(i);
                    createFunction(args, "Circle");
                }
                break;
            case "P": //test
                DisplayHelper.drawLine(-400, 0, 400, 0);
                break;
            case "Command": //test
                DisplayHelper.getDisplay().getGraphics().drawLine(0, 0, 800, 500);
                break;
            case "Command1": //test 2
                DisplayHelper.getDisplay().getGraphics().drawRect(0, 0, 200, 200);
                break;
            case "C": //Create
                List<String> args = Arrays.asList(splitCommand);
                String[] argsArray = args.subList(2, args.size()).toArray(new String[args.size()]);
                createFunction(argsArray, commandArg1);
                break;
            case "R": //Read

                break;
            case "U": //Update

                break;
            case "D": //Delete

                break;
            default:

                break;

        }
    }

    private void createFunction(String[] splitCommand, String commandArg1) {
        if (commandArg1.equals("cp")) { //centerpoint
            DisplayHelper.createCenterPoint();
            System.out.println("center point create command");
        }

//                else if (commandArg1.equals("c")) {
//                    DisplayHelper.createCircle(
//                            Double.parseDouble(splitCommand[2]),
//                            Double.parseDouble(splitCommand[3]),
//                            Double.parseDouble(splitCommand[4]),
//                            Long.parseLong(splitCommand[5]));
//                }

        else {//try a generic shape creation
            try {
                //The following line insinuates that only the top level generic shape may be called on
                //One cannot create an "isosceles" Triangle for instance

                //In reality, the client side coordinate system should really just be taking in shapes
                //From the kotlin server.
                //to which, the producers of the shapes and movement information is being fed into kotlin

                Constructor<?> constructor = Class.forName("MainSystem." + commandArg1 + "." + commandArg1).getConstructor(String[].class);
                DisplayHelper.createGenericShape(constructor.newInstance((Object) splitCommand));
            } catch (ClassNotFoundException e1) {
                System.out.println("ClassNotFoundException");
                e1.printStackTrace();
            } catch (NoSuchMethodException e1) {
                System.out.println("NoSuchMethodException");
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                System.out.println("IllegalAccessException");
                e1.printStackTrace();
            } catch (InstantiationException e1) {
                System.out.println("InstantiationException");
                e1.printStackTrace();
            } catch (InvocationTargetException e1) {
                System.out.println("InvocationTargetException");
                e1.printStackTrace();
            }
        }
    }
}
