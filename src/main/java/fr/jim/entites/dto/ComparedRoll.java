package fr.jim.entites.dto;

public class ComparedRoll {

    private Roll roll;

    private String comparator;

    private int comparedNumber;

    private String[] comparators = {"<", ">"};

    public ComparedRoll(String stringRoll) {
        this.construireComparedRoll(stringRoll);

    }

    private void construireComparedRoll(String stringRoll) {

        this.roll = new Roll(stringRoll, true);

        int comparatorIndex = 0;
        String foundComparator = null;

        for (String comp : comparators) {
            int index = stringRoll.indexOf(comp);
            if (index != -1) {
                comparatorIndex = index;
                foundComparator = comp;
                break;
            }
        }

        this.comparator = foundComparator;
        this.comparedNumber = Integer
                .parseInt(stringRoll.substring(comparatorIndex + foundComparator.length()).trim());
    }

    public Roll getRoll() {
        return roll;
    }

    public String getComparator() {
        return comparator;
    }

    public int getComparedNumber() {
        return comparedNumber;
    }

    public boolean isSuccess() {
        switch (this.comparator) {
            case "<":
                return this.getRoll().getTotal() < comparedNumber;
            case ">":
                return this.getRoll().getTotal() > comparedNumber;
        }
        return false;
    }
}
