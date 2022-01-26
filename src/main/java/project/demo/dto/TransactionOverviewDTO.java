package project.demo.dto;

import java.util.Comparator;
import java.util.Objects;

public class TransactionOverviewDTO {
    String offsetAccountHolder;
    String offsetAccountIban;
    String description;
    String date;
    String amountInCents;
    boolean received;
    long id;

    public TransactionOverviewDTO(String offsetAccountHolder, String offsetAccountIban, String description, String date, long amountInCents, boolean received, long id) {
        this.offsetAccountHolder = offsetAccountHolder;
        this.offsetAccountIban = offsetAccountIban;
        this.description = description;
        this.date = date;
        if (amountInCents<100) this.amountInCents = "0"+amountInCents;
        else this.amountInCents = ""+amountInCents;
        this.received = received;
        this.id = id;
    }

    public String getOffsetAccountHolder() {
        return offsetAccountHolder;
    }

    public String getOffsetAccountIban() {
        return offsetAccountIban;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getAmountInCents() {
        return amountInCents;
    }

    public boolean isReceived() {
        return received;
    }

    public long getId() {
        return id;
    }

    public static class DateComparator implements Comparator<TransactionOverviewDTO>{
        @Override
        public int compare(TransactionOverviewDTO t1, TransactionOverviewDTO t2) {
            if (t2.getDate().compareTo(t1.getDate())!=0) return t2.getDate().compareTo(t1.getDate());
            else return (int) (t2.getId()-t1.getId());
        }
    }
}
