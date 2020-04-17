package ru.job4j.lsp;

import java.time.LocalDate;
import java.util.*;

import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;

public class ControllQualityTest {

    private Food cheese, milk, banana, coockie;

    @Before
    public void init() {
        LocalDate now = LocalDate.now();
        Date created = java.sql.Date.valueOf(now.minusDays(10));
        Date expaired = java.sql.Date.valueOf(now.plusDays(10));
        cheese = new Food("cheese", created, expaired, 300.0, 0);

        expaired = java.sql.Date.valueOf(now.minusDays(1));
        milk = new Food("milk", created, expaired, 40.0, 0);

        expaired = java.sql.Date.valueOf(now.plusDays(2));
        banana = new Food("banana", created, expaired, 120.0, 0);

        expaired = java.sql.Date.valueOf(now.plusDays(60));
        coockie = new Food("cookie", created, expaired, 70.0, 0);
    }

    @Test
    public void whenRedistribute() {
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();

        warehouse.offer(cheese);
        shop.offer(milk);
        trash.offer(banana);
        trash.offer(coockie);

        Map<Storage, PutInStorage> map = new HashMap<>() { {
            put(warehouse, new PutInWarehouse());
            put(shop, new PutInShop());
            put(trash, new PutInTrash());
        } };

        new ControllQuality().redistribute(map);

        List<Food> inShop = new ArrayList<>(List.of(shop.poll(), shop.poll()));
        inShop.sort(Comparator.comparing(Food::getName));

        assertThat(warehouse.poll().getName(), is("cookie"));
        assertThat(inShop.get(0).getName(), is("banana"));
        assertThat(inShop.get(0).getDiscount(), is(20.0));
        assertThat(inShop.get(1).getName(), is("cheese"));
        assertThat(trash.poll().getName(), is("milk"));
    }

    @Test
    public void whenDistributeFood() {
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();

        Map<Storage, PutInStorage> map = new HashMap<>() { {
            put(warehouse, new PutInWarehouse());
            put(shop, new PutInShop());
            put(trash, new PutInTrash());
        } };

        new ControllQuality().distribute(map, cheese);

        assertThat(warehouse.poll(), is(nullValue()));
        assertThat(shop.poll().getName(), is("cheese"));
        assertThat(trash.poll(), is(nullValue()));
    }

    @Test
    public void whenDistributeListFood() {
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();

        Map<Storage, PutInStorage> map = new HashMap<>() { {
            put(warehouse, new PutInWarehouse());
            put(shop, new PutInShop());
            put(trash, new PutInTrash());
        } };

        List<Food> foods = List.of(cheese, milk, banana, coockie);

        new ControllQuality().distribute(map, foods);

        List<Food> inShop = new ArrayList<>(List.of(shop.poll(), shop.poll()));
        inShop.sort(Comparator.comparing(Food::getName));

        assertThat(warehouse.poll().getName(), is("cookie"));
        assertThat(inShop.get(0).getName(), is("banana"));
        assertThat(inShop.get(0).getDiscount(), is(20.0));
        assertThat(inShop.get(1).getName(), is("cheese"));
        assertThat(trash.poll().getName(), is("milk"));
    }
}