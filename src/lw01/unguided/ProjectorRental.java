package lw01.unguided;

public class ProjectorRental extends Rental {

    public ProjectorRental(String id, int days) {
        super(id, days);
    }

    @Override
    public int calculateCharge() {
        int firstThreeDays = Math.min(getDays(), 3);
        int additionalDays = Math.max(getDays() - 3, 0);

        return (firstThreeDays * 60000) + (additionalDays * 45000) + 20000;
    }

    @Override
    public String label() {
        return "Projector";
    }
}