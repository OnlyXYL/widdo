package cn.widdo.study.base;

/**
 * Base.
 *
 * @author XYL
 * @date 2023/05/29 17:09
 * @since 305.2.0.1
 */
public class Base {

    /**
     * constructor has none params.
     */
    protected Base() {
    }

    public static void main(String[] args) {
        final Base base = new Base();
        final Base base1 = new Base();
        base1.equals(base);
        "".isEmpty();
    }
}
