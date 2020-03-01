package ru.job4j.price;

import java.util.*;

public class Price implements Comparable<Price> {
    String productCode;     // код товара
    int number;             // номер цены
    int depart;             // номер отдела
    Date begin;             // начало действия
    Date end;               // конец действия
    long value;             // значение цены в копейках

    public Price(String productCode, int number,  int depart,  Date begin, Date end, long value) {
        this.productCode = productCode;
        this.number = number;
        this.depart = depart;
        this.begin = begin;
        this.end = end;
        this.value = value;
    }

    public static List<Price> putPrices(List<Price> currentPrice, List<Price> newPrice) {

        List<Price> listOut = new ArrayList<>();

        List<Price> listCur = new LinkedList<>(currentPrice);
        List<Price> listNew = new LinkedList<>(newPrice);
        Collections.sort(listCur);
        Collections.sort(listNew);

        while (listCur.size() > 0 && listNew.size() > 0) {

            int res = listNew.get(0).compareWithoutDate(listCur.get(0));

            if (res > 0) {
                listOut.add(listCur.remove(0));
            } else if (res < 0) {
                listOut.add(listNew.remove(0));
            } else {
                int pos = Price.relativePosition(listCur.get(0), listNew.get(0));

                switch (pos) {
                    case 1: if (listCur.get(0).value == listNew.get(0).value) {
                                listNew.get(0).begin = listCur.get(0).begin;
                                listCur.remove(0);
                            } else {
                                listOut.add(listCur.remove(0));
                            }
                            break;
                    case 2: if (listCur.get(0).value == listNew.get(0).value) {
                                 listCur.get(0).end = listNew.get(0).end;
                                 listOut.add(listCur.remove(0));
                                 listNew.remove(0);
                            } else {
                                 listCur.get(0).end = listNew.get(0).begin;
                                 listOut.add(listCur.remove(0));
                            }
                            break;
                    case 3: if (listCur.get(0).value == listNew.get(0).value) {
                                 listOut.add(listCur.remove(0));
                                 listNew.remove(0);
                            } else {
                                 listOut.add(new Price(listCur.get(0).productCode, listCur.get(0).number, listCur.get(0).depart, listCur.get(0).begin, listNew.get(0).begin, listCur.get(0).value));
                                 listCur.get(0).begin = listNew.get(0).end;
                                 listOut.add(listNew.remove(0));
                            }
                            break;
                    case 4: if (listCur.get(0).value == listNew.get(0).value) {
                                 listCur.get(0).begin = listNew.get(0).begin;
                                 listOut.add(listCur.remove(0));
                                 listNew.remove(0);
                            } else {
                                 listCur.get(0).begin = listNew.get(0).end;
                                 listOut.add(listNew.remove(0));
                            }
                            break;
                    case 5: if (listCur.get(0).value == listNew.get(0).value) {
                                listCur.get(0).begin = listNew.get(0).begin;
                                listNew.remove(0);
                            } else {
                                listOut.add(listNew.remove(0));
                            }
                            break;
                    case 6: listCur.remove(0);
                            break;
                    default: break;
                }
            }
        }

        if (listCur.size() > 0) {
            listOut.addAll(listCur);
            listCur.clear();
        }

        if (listNew.size() > 0) {
            listOut.addAll(listNew);
            listNew.clear();
        }

        return listOut;
    }

    private static int relativePosition(Price curPrice, Price newPrice) {
        Date curBegin = curPrice.begin;
        Date curEnd = curPrice.end;
        Date newBegin = newPrice.begin;
        Date newEnd = newPrice.end;

        int result = 0;
        if (curEnd.before(newBegin) || curEnd.equals(newBegin)) {
            result = 1;
        } else if (curBegin.before(newBegin) && newBegin.before(curEnd) && (curEnd.before(newEnd) || curEnd.equals(newEnd))) {
            result = 2;
        } else if (curBegin.before(newBegin) && newEnd.before(curEnd)) {
            result = 3;
        } else if ((newBegin.before(curBegin) || newBegin.equals(curBegin)) && curBegin.before(newEnd) && newEnd.before(curEnd)) {
            result = 4;
        } else if (newEnd.before(curBegin) || newEnd.equals(curBegin)) {
            result = 5;
        } else if ((newBegin.before(curBegin) || newBegin.equals(curBegin)) && (curEnd.before(newEnd) || curEnd.equals(newEnd))) {
            result = 6;
        }
        return result;
    }

    private int compareWithoutDate(Price o) {
        int result = this.productCode.compareTo(o.productCode);
        if (result == 0) {
            result = Integer.compare(this.number, o.number);
        }
        if (result == 0) {
            result = Integer.compare(this.depart, o.depart);
        }
        return result;
    }

    @Override
    public int compareTo(Price o) {
        int result = compareWithoutDate(o);
        if (result == 0) {
            result = this.begin.compareTo(o.begin);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Price price = (Price) o;
        return Objects.equals(productCode, price.productCode)
            && number == price.number
                && depart == price.depart
                    && Objects.equals(begin, price.begin)
                        && Objects.equals(end, ((Price) o).end)
                            && value == price.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productCode, number, depart, begin, end, value);
    }

}
