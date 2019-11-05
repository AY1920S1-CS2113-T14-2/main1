package duke.command.ingredientCommand;

import duke.command.Command;
import duke.dish.DishList;
import duke.exception.DukeException;
import duke.ingredient.Ingredient;
import duke.ingredient.IngredientsList;
import duke.order.OrderList;
import duke.storage.FridgeStorage;
import duke.storage.OrderStorage;
import duke.ui.Ui;

import java.text.SimpleDateFormat;

/**
 * Represents a specific {@link Command} used to find a String occurring in the {@link Ingredient}.
 */
public class FindIngredientCommand extends Command {

    private String toFind;
    private SimpleDateFormat simpleDateFormat;

    public FindIngredientCommand(String toFind) {
        this.toFind = toFind;
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }

    @Override
    public void execute(IngredientsList ingList, DishList dl, OrderList ol, Ui ui, FridgeStorage fs, OrderStorage os) throws DukeException {
        int i = 1;
        StringBuilder sb = new StringBuilder();

        for (Ingredient ingredient : ingList.getAllEntries()) {     //for every ingredient, scan through the ingredientslist
            i += 1;
            if (ingredient.getName().equals(toFind))
            {
                sb.append("\t ").append(i-1).append(". ").append(ingredient.getName()).append(" ").append(ingredient.getAmount()).append(" ").append(simpleDateFormat.format(ingredient.getExpiryDate())).append(".");
                sb.append(System.lineSeparator());
            }
        }
        if (sb.length() == 0) {
            System.out.println("No such ingredient found!");
        } else {
            System.out.println("\t These are the ingredients you searched for!");
            ui.showTask(sb.toString());
        }
    }
}
