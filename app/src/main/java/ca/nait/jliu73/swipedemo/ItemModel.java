package ca.nait.jliu73.swipedemo;

import java.io.Serializable;

/**
 * Created by Hank on 2019-04-02.
 */

public class ItemModel implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String itemName;

    public ItemModel(String itemName)
    {
        this.itemName = itemName;
    }

    public String getItemName()
    {
        return itemName;
    }

    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }
}
