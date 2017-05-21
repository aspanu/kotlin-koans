package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override operator fun compareTo(d2: MyDate): Int {
        if (d2.year == year && d2.month == month && d2.dayOfMonth == dayOfMonth) return 0
        else if (year > d2.year || year == d2.year && month > d2.month || year == d2.year && month == d2.month && dayOfMonth > d2.dayOfMonth) return 1
        else return -1
    }

    operator fun rangeTo(other: MyDate) : DateRange {
        return DateRange(start = this, endInclusive = other)
    }

    operator fun plus(timeInterval: TimeInterval): MyDate = this.addTimeIntervals(timeInterval, 1)
    operator fun plus(rti: RepeatedTimeInterval): MyDate = this.addTimeIntervals(rti.timeInterval, rti.number)
}

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR;

    operator fun times(i: Int): RepeatedTimeInterval {
        return RepeatedTimeInterval(this, i)
    }

}

data class RepeatedTimeInterval(val timeInterval: TimeInterval, val number: Int)

data class MyDateIterator(val start: MyDate, val endInclusive: MyDate) : Iterator<MyDate> {
    var currentDate = start

    override fun hasNext(): Boolean {
        return this.currentDate <= endInclusive
    }

    override fun next(): MyDate {
        val result : MyDate = this.currentDate
        this.currentDate = this.currentDate.nextDay()
        return result
    }

}

class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate>, Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> = MyDateIterator(start, endInclusive)
}