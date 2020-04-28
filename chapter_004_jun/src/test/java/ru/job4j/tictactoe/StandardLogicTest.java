package ru.job4j.tictactoe;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@Ignore
public class StandardLogicTest {

    @Test
    public void whenHasXDiagonalWinner() {
        Field field = new Field(3);
        field.setMarkX(0, 0);
        field.setMarkX(1, 1);
        field.setMarkX(2, 2);
        Logic standartLogic = new StandardLogic(field);
        assertThat(standartLogic.isWinnerX(), is(true));
    }

    @Test
    public void whenHasXBackDiagonalWinner() {
        Field field = new Field(3);
        field.setMarkX(0, 2);
        field.setMarkX(1, 1);
        field.setMarkX(2, 0);
        Logic standartLogic = new StandardLogic(field);
        assertThat(standartLogic.isWinnerX(), is(true));
    }

    @Test
    public void whenHasXHorizontalWinner() {
        Field field = new Field(3);
        field.setMarkX(1, 0);
        field.setMarkX(1, 1);
        field.setMarkX(1, 2);
        Logic standartLogic = new StandardLogic(field);
        assertThat(standartLogic.isWinnerX(), is(true));
    }

    @Test
    public void whenHasXVerticalWinner() {
        Field field = new Field(3);
        field.setMarkX(0, 1);
        field.setMarkX(1, 1);
        field.setMarkX(2, 1);
        Logic standartLogic = new StandardLogic(field);
        assertThat(standartLogic.isWinnerX(), is(true));
    }

    @Test
    public void whenHasODiagonalWinner() {
        Field field = new Field(3);
        field.setMarkO(0, 0);
        field.setMarkO(1, 1);
        field.setMarkO(2, 2);
        Logic standartLogic = new StandardLogic(field);
        assertThat(standartLogic.isWinnerO(), is(true));
    }

    @Test
    public void whenHasOBackDiagonalWinner() {
        Field field = new Field(3);
        field.setMarkO(0, 2);
        field.setMarkO(1, 1);
        field.setMarkO(2, 0);
        Logic standartLogic = new StandardLogic(field);
        assertThat(standartLogic.isWinnerO(), is(true));
    }

    @Test
    public void whenHasOHorizontalWinner() {
        Field field = new Field(3);
        field.setMarkO(1, 0);
        field.setMarkO(1, 1);
        field.setMarkO(1, 2);
        Logic standartLogic = new StandardLogic(field);
        assertThat(standartLogic.isWinnerO(), is(true));
    }

    @Test
    public void whenHasOVerticalWinner() {
        Field field = new Field(3);
        field.setMarkO(0, 1);
        field.setMarkO(1, 1);
        field.setMarkO(2, 1);
        Logic standartLogic = new StandardLogic(field);
        assertThat(standartLogic.isWinnerO(), is(true));
    }

    @Test
    public void whenNotFill() {
        Field field = new Field(3);
        field.setMarkX(0, 1);
        Logic standartLogic = new StandardLogic(field);
        assertThat(standartLogic.isWinnerX(), is(false));
        assertThat(standartLogic.isWinnerO(), is(false));
    }

    @Test
    public void whenHasGap() {
        Field field = new Field(2);
        field.setMarkX(0, 0);
        field.setMarkX(1, 1);
        Logic standartLogic = new StandardLogic(field);
        assertThat(standartLogic.hasGap(), is(true));
    }

    @Test
    public void whenNotHasGap() {
        Field field = new Field(2);
        field.setMarkX(0, 0);
        field.setMarkX(0, 1);
        field.setMarkX(1, 0);
        field.setMarkX(1, 1);
        Logic standartLogic = new StandardLogic(field);
        assertThat(standartLogic.hasGap(), is(false));
    }
}