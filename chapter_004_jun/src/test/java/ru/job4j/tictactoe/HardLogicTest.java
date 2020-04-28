package ru.job4j.tictactoe;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@Ignore
public class HardLogicTest {

    @Test
    public void whenHasXDiagonalWinner() {
        Field field = new Field(7);
        field.setMarkX(1, 1);
        field.setMarkX(2, 2);
        field.setMarkX(3, 3);
        field.setMarkX(4, 4);
        field.setMarkX(5, 5);
        Logic hardLogic = new HardLogic(field);
        assertThat(hardLogic.isWinnerX(), is(true));
    }

    @Test
    public void whenHasXBackDiagonalWinner() {
        Field field = new Field(7);
        field.setMarkX(5, 1);
        field.setMarkX(4, 2);
        field.setMarkX(3, 3);
        field.setMarkX(2, 4);
        field.setMarkX(1, 5);
        Logic hardLogic = new HardLogic(field);
        assertThat(hardLogic.isWinnerX(), is(true));
    }

    @Test
    public void whenHasXOtherDiagonalWinner() {
        Field field = new Field(7);
        field.setMarkX(5, 0);
        field.setMarkX(4, 1);
        field.setMarkX(3, 2);
        field.setMarkX(2, 3);
        field.setMarkX(1, 4);
        Logic hardLogic = new HardLogic(field);
        assertThat(hardLogic.isWinnerX(), is(true));
    }

    @Test
    public void whenHasXHorizontalWinner() {
        Field field = new Field(7);
        field.setMarkX(1, 0);
        field.setMarkX(1, 1);
        field.setMarkX(1, 2);
        field.setMarkX(1, 3);
        field.setMarkX(1, 4);
        Logic hardLogic = new HardLogic(field);
        assertThat(hardLogic.isWinnerX(), is(true));
    }

    @Test
    public void whenHasXVerticalWinner() {
        Field field = new Field(7);
        field.setMarkX(0, 1);
        field.setMarkX(1, 1);
        field.setMarkX(2, 1);
        field.setMarkX(3, 1);
        field.setMarkX(4, 1);
        Logic hardLogic = new HardLogic(field);
        assertThat(hardLogic.isWinnerX(), is(true));
    }

    @Test
    public void whenHasODiagonalWinner() {
        Field field = new Field(7);
        field.setMarkO(0, 0);
        field.setMarkO(1, 1);
        field.setMarkO(2, 2);
        field.setMarkO(3, 3);
        field.setMarkO(4, 4);
        Logic hardLogic = new HardLogic(field);
        assertThat(hardLogic.isWinnerO(), is(true));
    }

    @Test
    public void whenNotFill() {
        Field field = new Field(7);
        field.setMarkX(0, 1);
        Logic hardLogic = new HardLogic(field);
        assertThat(hardLogic.isWinnerX(), is(false));
        assertThat(hardLogic.isWinnerO(), is(false));
    }

    @Test
    public void whenHasGap() {
        Field field = new Field(7);
        field.setMarkX(0, 0);
        Logic hardLogic = new HardLogic(field);
        assertThat(hardLogic.hasGap(), is(true));
    }

    @Test
    public void whenNotHasGap() {
        Field field = new Field(7);
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                field.setMarkX(i, j);
            }
        }
        Logic hardLogic = new HardLogic(field);
        assertThat(hardLogic.hasGap(), is(false));
    }

    @Test(expected = IllegalStateException.class)
    public void whenSmallField() {
        Field field = new Field(4);
        Logic hardLogic = new HardLogic(field);
    }
}