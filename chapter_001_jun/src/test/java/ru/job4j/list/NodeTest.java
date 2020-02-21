package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class NodeTest {
    Node<Integer> first, second, third, fourth;

    @Before
    public void beforeTest() {
        first = new Node<>(1);
        second = new Node<>(2);
        third = new Node<>(3);
        fourth = new Node<>(4);
    }

    @Test
    public void whenOneElementsNotCycle() {
        assertThat(Node.hasCycle(first), is(false));
    }

    @Test
    public void whenTwoElementsNotCycle() {
        first.next = second;
        assertThat(Node.hasCycle(first), is(false));
    }

    @Test
    public void whenFourNotCycle() {
        first.next = second;
        second.next = third;
        third.next = fourth;
        assertThat(Node.hasCycle(first), is(false));
    }

    @Test
    public void whenFourHasCycle() {
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = first;
        assertThat(Node.hasCycle(first), is(true));
    }

    @Test
    public void whenTwoHasCycle() {
        first.next = second;
        second.next = first;
        assertThat(Node.hasCycle(first), is(true));
    }

    @Test
    public void whenFourHasCycleInEnd() {
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = third;
        assertThat(Node.hasCycle(first), is(true));
    }
}