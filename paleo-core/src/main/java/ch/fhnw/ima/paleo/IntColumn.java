package ch.fhnw.ima.paleo;

import java.util.Arrays;
import java.util.stream.IntStream;

import static ch.fhnw.ima.paleo.ColumnIds.IntColumnId;

public final class IntColumn implements Column<IntColumnId> {

    private final IntColumnId id;
    private final int[] values;

    public IntColumn(IntColumnId id, IntStream values) {
        this.id = id;
        this.values = values.toArray();
    }

    public static Builder builder(IntColumnId id) {
        return new Builder(id);
    }

    @Override
    public IntColumnId getColumnId() {
        return this.id;
    }

    @Override
    public int getRowCount() {
        return this.values.length;
    }

    public int getValueAt(int index) {
        return this.values[index];
    }

    public IntStream getValues() {
        return Arrays.stream(this.values);
    }

    public static final class Builder {

        private final IntColumnId id;
        private final IntStream.Builder valueBuilder;

        public Builder(IntColumnId id) {
            this.id = id;
            this.valueBuilder = IntStream.builder();
        }

        public Builder addAll(int... values) {
            for (int value : values) {
                add(value);
            }
            return this;
        }

        public Builder add(int value) {
            this.valueBuilder.add(value);
            return this;
        }

        public IntColumn build() {
            return new IntColumn(this.id, this.valueBuilder.build());
        }

    }

}