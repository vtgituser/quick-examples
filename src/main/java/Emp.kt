import org.apache.commons.lang3.builder.EqualsBuilder
import org.apache.commons.lang3.builder.HashCodeBuilder
import org.apache.commons.lang3.builder.ToStringBuilder

class Emp {
    private var id: Int = 0
    private var first: String? = null
    private var last: String? = null
    private var active: Boolean = false
    private var manager: Emp? = null

    val name: String
        get() = "$first $last"

    constructor(id: Int, first: String, last: String, active: Boolean) {
        this.id = id
        this.first = first
        this.last = last
        this.active = active
    }

    constructor(id: Int, first: String, last: String) {
        this.id = id
        this.first = first
        this.last = last
        this.active = true
    }

    fun getId(): Int {
        return id
    }

    fun setId(id: Int): Emp {
        this.id = id
        return this
    }

    fun getFirst(): String? {
        return first
    }

    fun setFirst(first: String): Emp {
        this.first = first
        return this
    }

    fun getLast(): String? {
        return last
    }

    fun setLast(last: String): Emp {
        this.last = last
        return this
    }

    fun isActive(): Boolean {
        return active
    }

    fun setActive(active: Boolean): Emp {
        this.active = active
        return this
    }

    fun getManager(): Emp? {
        return manager
    }

    fun setManager(manager: Emp): Emp {
        this.manager = manager
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }

        if (o == null || javaClass != o.javaClass) {
            return false
        }

        val emp = o as Emp?

        return EqualsBuilder()
                .append(id, emp!!.id)
                .append(active, emp.active)
                .append(first, emp.first)
                .append(last, emp.last)
                .append(manager, emp.manager)
                .isEquals
    }

    override fun hashCode(): Int {
        return HashCodeBuilder(17, 37)
                .append(id)
                .append(first)
                .append(last)
                .append(active)
                .append(manager)
                .toHashCode()
    }

    override fun toString(): String {
        return ToStringBuilder(this)
                .append("id", id)
                .append("first", first)
                .append("last", last)
                .append("active", active)
                .append("manager", manager)
                .toString()
    }
}
