package syntax.sealed;

public class SealedTest {

    // jdk 15 new feature preview
    // syntax.sealed class 限定了一个类的子类的范围，该类被称为封闭类
}

sealed class Animal permits Dog, Cat {

}

final class Dog extends Animal {

}

sealed class Cat extends Animal permits BlackCat, WhiteCat {

}

final class BlackCat extends Cat {

}

non-sealed class WhiteCat extends Cat {

}