# رونوشت گفت‌وگو — پروژه TDD Shopping Cart

> این فایل شامل پیام‌های قابل‌مشاهده‌ی کاربر و دستیار در این گفت‌وگو است. پیام‌های سیستمی، دستورالعمل‌های داخلی و خروجی‌های مخفی ابزارها در این رونوشت نیامده‌اند.

---

## کاربر

در انجام گام به گام این پروژه توضیحات لازمی که باید داده شود و کارهایی که نیاز است تا انجام دهم را بگو.
در حال انجام پروژه بصورت تک نفره هستم ولی خب میشه عمده چیز هایش را تک نفره هم انجام داد
`@SELab-Plc-Summer-2025.pdf` دستور العمل ازمایشگاه هم گذاشتم. طبق این readme کامل را به من بده برای گزارش کار. سپس توضیح بده که چه نکاتی مهم است که به آنها اشاره کنم و چیز های خواسته شده مسئله چیست. (بجای همگیت هم از گیتهاب استفاده میکنم و توضیحات مربوط به استفاده از کانبان را هم بگو) همه ی موارد نیاز از جمله کد های هر بخش را هم به من بده به همراه توضیحات مربوط. از صفر تا صد بگو چیکار باید بکنم. codex هم در اکستنشن vscode دارم. (کلن از همون vscode استفاده میکنم)

### فایل پیوست‌شده
- `آزمایش سوم - مفاهیمی در آزمون نرم_افزار.pdf`

---

## دستیار

بله؛ طبق PDF، این پروژه را می‌توان **تک‌نفره** انجام داد. چیزی که برای ارزیابی مهم است تعداد اعضای تیم نیست، بلکه این است که روند واقعی **تحلیل → Red → Green → Refactor → Coverage → Mutation Testing → مستندسازی Codex** در Git history و گزارش قابل مشاهده باشد.

یک نکته‌ی مهم: من توانستم صورت‌مسئله را کامل بررسی کنم، ولی مخزن Hamgit معرفی‌شده در PDF از این محیط قابل بازیابی نبود. بنابراین کدهایی که پایین می‌دهم بر اساس **قرارداد رفتاری دقیق صورت‌مسئله، Java + JUnit 5 + Maven + JaCoCo + PIT** نوشته شده‌اند. اگر نام package، نام فیلدها یا امضای بعضی متدهای پروژه پایه متفاوت باشد، فقط همان قسمت‌ها را با پروژه خودت تطبیق بده؛ **تست‌های اصلی پروژه را تغییر نده**.

# 1. اول دقیقاً بدان پروژه چه می‌خواهد

موضوع آزمایش **Test Driven Development** است و هدف این است که بتوانی نیازمندی را از روی کد و صورت‌مسئله استخراج کنی، تست واحد طراحی کنی، Edge Caseها و خطاها را پوشش بدهی و کیفیت تست را هم با Coverage و Mutation Testing ارزیابی کنی.

سیستم یک Shopping Cart است که باید حداقل این رفتارها را داشته باشد:

- افزودن کالا با نام و قیمت
- حذف کالا بر اساس نام
- محاسبه مجموع
- اعمال تخفیف ۱۰٪ فقط وقتی مجموع **بیشتر از 100** است
- مجموع دقیقاً 100 تخفیف ندارد
- تخفیف نباید مقدار پایه سبد را دائماً دستکاری کند
- قیمت‌های اعشاری باید درست محاسبه شوند
- سبد خالی، حذف کالای ناموجود، حذف چندباره و کالاهای تکراری باید رفتار مشخص داشته باشند.

علاوه بر این باید:

1. Baseline Analysis انجام دهی.
2. حداقل **۳ باگ مستقل** پیدا و با TDD اصلاح کنی.
3. `updateItemPrice(String itemName, double newPrice)` را با TDD بسازی.
4. برای آن **حداقل ۸ تست معنادار قبل از پیاده‌سازی** داشته باشی.
5. یک قابلیت تجاری دوم را با TDD پیاده‌سازی کنی.
6. تست‌های مرزی، exception و parameterized داشته باشی.
7. JaCoCo اجرا کنی.
8. PIT Mutation Testing اجرا کنی.
9. حداقل **۱۲ تعامل معنادار با Codex** را مستند کنی.
10. Commit history مرحله‌ای داشته باشی.
11. Failure و Successهای مهم را در گزارش نشان بدهی.

# 2. ترتیب کاری که پیشنهاد می‌کنم

```text
0. GitHub repository
       ↓
1. Baseline
       ↓
2. Initial Coverage
       ↓
3. Bug #1: Red → Green → Refactor
       ↓
4. Bug #2: Red → Green → Refactor
       ↓
5. Bug #3: Red → Green → Refactor
       ↓
6. updateItemPrice
   8+ tests → Red → Green → Refactor
       ↓
7. Business Feature #2
   Red → Green → Refactor
       ↓
8. Advanced Tests
       ↓
9. Final Coverage
       ↓
10. PIT Mutation Testing
       ↓
11. Improve tests for surviving mutants
       ↓
12. Final Mutation report
       ↓
13. README + Codex log + screenshots
```

# 3. ساخت پروژه روی GitHub

چون گفتی به‌جای Hamgit از GitHub استفاده می‌کنی، workflow را با GitHub انجام بده.

```bash
git clone <YOUR-GITHUB-REPOSITORY-URL>
cd <repository-name>
code .
```

اگر مخزن اولیه را از Hamgit گرفته‌ای:

```bash
git clone https://hamgit.ir/sqrlab-public/base-project-for-tdd-shoppingcart
cd base-project-for-tdd-shoppingcart

git remote rename origin upstream
git remote add origin https://github.com/YOUR_USERNAME/tdd-shopping-cart.git
git push -u origin main
```

# 4. GitHub Kanban

Statusها:

```text
Backlog
Ready
Red
Green
Refactor
Testing / Analysis
Done
```

Issueهای پیشنهادی:

```text
#1 Baseline analysis
#2 Initial JaCoCo coverage
#3 Bug: removing missing item
#4 Bug: decimal precision
#5 Bug: invalid price
#6 Implement updateItemPrice
#7 Implement maximum cart capacity
#8 Parameterized and boundary tests
#9 Final JaCoCo report
#10 PIT mutation testing
#11 Kill surviving mutants
#12 Final README/report
```

برای هر Issue:

```markdown
## Goal
توضیح رفتار مورد نظر

## Acceptance Criteria
- [ ] تست Red نوشته شده
- [ ] Failure ثبت شده
- [ ] کمترین implementation انجام شده
- [ ] همه تست‌ها سبز هستند
- [ ] Refactor انجام شده
- [ ] Regression test اجرا شده
- [ ] Commitهای مربوط ثبت شده
- [ ] تعامل Codex در صورت استفاده مستند شده
```

# 5. Commit strategy

```bash
git commit -m "test: expose missing-item removal bug"
git commit -m "fix: handle removal of missing item safely"
git commit -m "refactor: simplify cart item lookup"

git commit -m "test: add failing tests for updateItemPrice"
git commit -m "feat: implement minimal updateItemPrice behavior"
git commit -m "refactor: centralize item lookup and validation"
```

از یک commit بزرگ مثل `complete project` خودداری کن.

# 6. ساختار پیشنهادی repository

```text
tdd-shopping-cart/
│
├── README.md
├── pom.xml
├── .gitignore
├── docs/
│   ├── baseline.md
│   ├── bugs.md
│   ├── codex-interactions.md
│   ├── coverage.md
│   ├── mutation-testing.md
│   └── screenshots/
├── src/
└── target/
```

# 7. VS Code

Extensionهای مفید:

```text
Extension Pack for Java
Maven for Java
Test Runner for Java
GitHub Pull Requests and Issues
Codex
```

```bash
java -version
mvn -version
mvn clean test
```

# 8. Baseline Analysis

موارد موردنیاز:

- وضعیت اجرای اولیه
- تست‌های موجود
- ساختار کلاس‌ها
- مسئولیت ShoppingCart
- روش نگهداری itemها
- محاسبه total
- منطق discount
- Edge Caseهای بدون تست
- Coverage اولیه

# 9. JaCoCo

```xml
<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>0.8.12</version>
</plugin>
```

```bash
mvn clean verify
```

گزارش:

```text
target/site/jacoco/index.html
```

# 10. سه باگ

برای هر باگ باید Root Cause، تست آشکارکننده، Failure، fix و regression ثبت شود.

# 11. نمونه تست باگ

```java
@Test
void removingMissingItemShouldFailWithoutChangingCart() {
    ShoppingCart cart = new ShoppingCart();
    cart.addItem("Book", 40.0);

    assertThrows(
        IllegalArgumentException.class,
        () -> cart.removeItem("Laptop")
    );

    assertEquals(40.0, cart.getTotal(), 0.0001);
}
```

# 12. قیمت نامعتبر

```java
@ParameterizedTest
@ValueSource(doubles = {0.0, -1.0, -100.0})
void itemPriceMustBePositive(double invalidPrice) {
    ShoppingCart cart = new ShoppingCart();

    assertThrows(
        IllegalArgumentException.class,
        () -> cart.addItem("Book", invalidPrice)
    );
}
```

# 13. دقت اعشاری

```java
assertEquals(expected, actual, 0.0001);
```

# 14. تست تخفیف

```java
@Test
void totalExactly100ShouldNotReceiveDiscount() {
    ShoppingCart cart = new ShoppingCart();
    cart.addItem("A", 100.0);
    assertEquals(100.0, cart.getDiscountedTotal(), 0.0001);
}
```

# 15. updateItemPrice

حداقل ۸ تست پیش از implementation:

1. update موفق
2. item count ثابت
3. کالای ناموجود
4. عدم side effect
5. null
6. empty
7. صفر
8. منفی

بهتر است decimal و اثر روی discount هم تست شوند.

# 16. RED واقعی updateItemPrice

اول تست‌ها، بعد commit:

```bash
git commit -m "test: define updateItemPrice behavior"
```

# 17. GREEN updateItemPrice

نمونه:

```java
public void updateItemPrice(String itemName, double newPrice) {
    validateItemName(itemName);
    validatePrice(newPrice);
    // ...
}
```

# 18. REFACTOR

کد lookup/validation مشترک را استخراج کن و بعد تمام تست‌ها را دوباره اجرا کن.

# 19. کالاهای تکراری

Policy باید روشن و تست‌شده باشد.

# 20. قابلیت تجاری دوم

پیشنهاد: **Maximum Cart Capacity**

```text
حداکثر 100 کالا
کالای 101ام fail
failure بدون تغییر state
```

# 21 تا 25. تست‌های قابلیت دوم، Parameterized، اعداد بزرگ و توالی چندمرحله‌ای

برای ظرفیت، invalid input، decimal، large finite values و سناریوی Add → Remove → Update تست داشته باش.

# 26. Mockito

اگر dependency خارجی وجود ندارد، Mock مصنوعی اضافه نکن.

# 27. PIT Mutation Testing

```bash
mvn org.pitest:pitest-maven:mutationCoverage
```

# 28. تفاوت Coverage و Mutation

Coverage فقط نشان می‌دهد کد اجرا شده؛ Mutation Testing قدرت تست در کشف خطای مصنوعی را می‌سنجد.

# 29. نمونه mutant

`>` ممکن است به `>=` تبدیل شود؛ تست مرز دقیقاً 100 باید آن را بکشد.

# 30 و 31. Codex

حداقل ۱۲ تعامل مستند با prompt، پاسخ، ارزیابی خودت، تصمیم نهایی و commit مرتبط.

# 32. از Codex کل پروژه را یکجا تولید نکن

از Codex به‌عنوان reviewer/analysis assistant استفاده کن.

# 33. README نهایی

README باید اهداف، مراحل TDD، baseline، سه bug، updateItemPrice، feature دوم، coverage، mutation، Codex، Git/Kanban و دستورات بازتولید نتایج را پوشش دهد.

# 34. Screenshotهای مهم

```text
01-baseline-tests.png
02-baseline-jacoco.png
03-bug1-red.png
04-bug1-green.png
05-bug2-red.png
06-bug2-green.png
07-bug3-red.png
08-bug3-green.png
09-update-price-red.png
10-update-price-green.png
11-business-feature-red.png
12-business-feature-green.png
13-final-tests.png
14-final-jacoco.png
15-pit-initial.png
16-pit-final.png
17-github-project.png
18-git-history.png
```

# 35 تا 37. نکات نمره‌ساز و Checklist

برای هر باگ دلیل، ورودی آشکارکننده، Red، Root Cause، Fix و Regression را ثبت کن. تست‌های اصلی را بی‌دلیل تغییر نده، Red واقعی و Git history مرحله‌ای داشته باش، JaCoCo/PIT و ۱۲ تعامل Codex را فراموش نکن.

---

## کاربر

[تصویر Kanban در GitHub Projects]

فعلا گزارش رو بیخیال شیم. این صفحه کانبانه. بیا مراحل گزارش و خروجی هایی که نیازه ذخیره کنم رو بگو. بعد اون هارو برای نوشتن گزارش بهت میدم

### تصویر پیوست‌شده
- `eff931d6-b23f-4725-b630-6a0c82938fc4.png`

---

## دستیار

فعلاً گزارش را کنار بگذاریم. هدف این است که در حین انجام پروژه تمام evidence لازم را جمع کنی.

همین ستون‌های فعلی کافی‌اند:

**Backlog → Ready → In progress → In review → Done**

برای هر کارت checklist داخلی TDD داشته باش:

```text
- Analyze requirements
- Write failing tests (RED)
- Save failure evidence
- Commit RED
- Implement minimum solution (GREEN)
- Save success evidence
- Commit GREEN
- Refactor
- Run regression tests
- Commit REFACTOR
- Update evidence files
```

ساختار پیشنهادی evidence:

```text
docs/
├── evidence/
│   ├── 00-baseline/
│   ├── 01-bug-1/
│   ├── 02-bug-2/
│   ├── 03-bug-3/
│   ├── 04-update-item-price/
│   ├── 05-business-feature/
│   ├── 06-advanced-tests/
│   ├── 07-coverage/
│   ├── 08-mutation/
│   ├── 09-codex/
│   └── 10-git-kanban/
└── notes/
```

## مرحله 0 — قبل از هر تغییر

```bash
git status
git log --oneline --all
mvn clean test
```

ذخیره:

```text
01-initial-project.png
02-initial-test-run.png
03-initial-git-log.png
test-output.txt
git-log.txt
```

## مرحله 1 — Coverage اولیه

```bash
mvn clean verify
```

ذخیره Line/Branch/Method و screenshot گزارش JaCoCo.

## Bug #1 تا #3

برای هر باگ:

```text
Observed/Suspected behavior
Expected behavior
Input that exposes bug
Why old tests missed it
RED test
RED output
RED commit
Minimal Fix
GREEN output
GREEN commit
Refactor
Refactor commit
Regression result
Codex interaction
```

## updateItemPrice

حداقل ۸ تست قبل از implementation و evidence کامل Red/Green/Refactor.

## Business Feature

Requirement، acceptance criteria، Red/Green/Refactor و regression.

## Advanced Tests

```text
empty cart
null
empty string
zero price
negative price
decimal price
very large price
duplicate item
missing item
repeated removal
exactly 100
below/above 100
add → remove → update
```

## Coverage نهایی

Baseline و Final Line/Branch/Method را کنار هم ذخیره کن.

## PIT

Initial score، surviving mutants، تست‌های اضافه‌شده و Final score.

## Codex

۱۲ interaction با قالب:

```text
Date
Project stage
Prompt
Response summary
My evaluation
Accepted
Rejected
Final decision
Related commit
```

## Git/Kanban evidence

```bash
git log --oneline --decorate --graph --all
```

سه screenshot از Kanban: اول، وسط، آخر.

### کارت‌های Kanban

- Baseline Analysis
- Initial JaCoCo Coverage
- Bug #1
- Bug #2
- Bug #3
- updateItemPrice
- Business Feature #2
- Advanced & Parameterized Tests
- Final JaCoCo Coverage
- PIT Mutation Testing
- Kill Surviving Mutants
- Final Verification
- Documentation Evidence

قاعده ساده:

**Red:** کد تست + Failure + commit hash  
**Green:** کد fix + تست موفق + commit hash  
**Refactor:** دلیل + تست موفق + commit hash

---

## کاربر

Here's the code, give me the step by step guide to do the experiments. Just tell me the things that I need to do and tell me how I should do them and what should I change

### فایل پیوست‌شده
- `base-project-for-tdd-shoppingcart.zip`

---

## دستیار

پروژه کوچک است و می‌شود آزمایش را کنترل‌شده جلو برد. نکته مهم: **کد نهایی را یکجا اعمال نکن**؛ هر مرحله Red/Green/Refactor را جدا انجام بده، evidence بگیر و commit کن.

ساختار پروژه:

```text
src/
├── ShoppingCart.java
├── Item.java
└── Main.java

Test/
└── ShoppingCartTest.java
```

وضعیت مهم `ShoppingCart`:

```text
Map<String, Double> items
addItem(...)
removeItem(...)
getTotal()
getTotalWithDiscount()
getItemCount()
updateItemPrice(String name, int newPrice) // empty
```

سه تست `updateItemPrice` از قبل کامنت شده‌اند.

# ترتیب دقیق کار

```text
1. Project Setup + Maven
2. Baseline Analysis
3. Initial JaCoCo Coverage
4. Bug #1 - Duplicate item
5. Bug #2 - Invalid item data
6. Bug #3 - Discount boundary
7. updateItemPrice - TDD
8. Business Feature - Cart Capacity
9. Advanced / Parameterized Tests
10. Final JaCoCo Coverage
11. PIT Mutation Testing
12. Kill Surviving Mutants
13. Final Verification
```

# مرحله 0

```bash
git status
git log --oneline
git tag starter-code
git push origin starter-code
```

# مرحله 1 — Maven

`pom.xml` اضافه کن و JUnit 5، Surefire، JaCoCo و PIT را پیکربندی کن. چون پروژه ساختار استاندارد Maven ندارد، `src` و `Test` را به‌عنوان source/test directory معرفی کن.

بعد:

```bash
java -version
mvn -version
git add pom.xml .gitignore
git commit -m "chore: add Maven and testing tools"
git push
```

# مرحله 2 — Baseline

بدون تغییر source/test:

```bash
mvn clean test
```

Evidence ذخیره کن.

در نسخه اولیه:

```java
private Map<String, Double> items = new HashMap<>();
```

و:

```java
public void addItem(String name, double price) {
    items.put(name, price);
}
```

در نتیجه validation وجود ندارد، duplicate overwrite می‌شود، null و قیمت غیرمعتبر پذیرفته می‌شوند.

همچنین شرط تخفیف فعلی:

```java
if (total >= 100)
```

است، در حالی که requirement می‌گوید `> 100`.

و:

```java
public void updateItemPrice(String name, int newPrice) {}
```

خالی است.

# مرحله 3 — Baseline JaCoCo

```bash
mvn clean verify
```

گزارش:

```text
target/site/jacoco/index.html
```

Line/Branch/Method را ذخیره کن و commit evidence بزن.

# مرحله 4 — Bug #1: Duplicate item

Policy پیشنهادی:

```text
duplicate name → IllegalArgumentException
cart unchanged
```

RED:

```java
@Test
void duplicateItemNameShouldBeRejectedWithoutChangingCart() {
    ShoppingCart cart = new ShoppingCart();
    cart.addItem("Book", 50.0);

    assertThrows(
            IllegalArgumentException.class,
            () -> cart.addItem("Book", 80.0)
    );

    assertEquals(1, cart.getItemCount());
    assertEquals(50.0, cart.getTotal(), 0.0001);
}
```

Commit:

```bash
git commit -m "test: expose duplicate item overwrite bug"
```

GREEN:

```java
public void addItem(String name, double price) {
    if (items.containsKey(name)) {
        throw new IllegalArgumentException(
                "Item already exists: " + name
        );
    }
    items.put(name, price);
}
```

Commit:

```bash
git commit -m "fix: reject duplicate item names"
```

# مرحله 5 — Bug #2: Invalid input

Tests برای null/blank name، zero/negative price، NaN/Infinity.

GREEN helperها:

```java
private void validateName(String name) {
    if (name == null || name.isBlank()) {
        throw new IllegalArgumentException(
                "Item name must not be blank"
        );
    }
}
```

```java
private void validatePrice(double price) {
    if (!Double.isFinite(price) || price <= 0.0) {
        throw new IllegalArgumentException(
                "Price must be a positive finite value"
        );
    }
}
```

# مرحله 6 — Bug #3: Discount boundary

Requirement:

```text
total > 100 → 10%
total == 100 → no discount
```

ولی کد فعلی `>= 100` دارد.

در starter test نیز تستی با نام `testDiscountAtBoundary_WRONG` وجود دارد که انتظار 90 برای total=100 دارد. این با PDF تناقض دارد. قبل از دستکاری تست اصلی، بهتر است از TA بپرسی آیا این تست عمداً برای اصلاح قرار داده شده است.

تست درست:

```java
@Test
void exactly100ShouldNotReceiveDiscount() {
    ShoppingCart cart = new ShoppingCart();
    cart.addItem("ItemA", 40.0);
    cart.addItem("ItemB", 60.0);

    assertEquals(
            100.0,
            cart.getTotalWithDiscount(),
            0.0001
    );
}
```

Fix implementation:

```java
if (total > 100)
```

# مرحله 7 — updateItemPrice

سه تست کامنت‌شده را uncomment کن. هنوز implementation ننویس.

بعد پنج تست دیگر برای decimal، zero، negative، null name و blank name اضافه کن تا حداقل ۸ تست قبل از implementation داشته باشی.

دو تست اضافه خوب:

```java
@Test
void updateShouldChangeTotalCorrectly() { ... }
```

و:

```java
@Test
void updateShouldAffectDiscountCalculation() { ... }
```

RED commit:

```bash
git commit -m "test: define updateItemPrice behavior"
```

GREEN:

signature را از `int` به `double` تغییر بده:

```java
public void updateItemPrice(String name, double newPrice) {
    validateName(name);
    validatePrice(newPrice);

    if (!items.containsKey(name)) {
        return;
    }

    items.put(name, newPrice);
}
```

چون starter test برای item ناموجود می‌گوید ShouldDoNothing، no-op انتخاب شد.

Commit:

```bash
git commit -m "feat: implement updateItemPrice"
```

Refactor و regression سپس commit جدا.

# مرحله 8 — قابلیت تجاری دوم

**Maximum Cart Capacity**

```text
Maximum items = 100
101st → IllegalStateException
failure → no state change
```

RED tests برای پذیرش 100، رد 101، و atomicity.

GREEN:

```java
private static final int MAX_ITEMS = 100;
```

و در `addItem`:

```java
if (items.size() >= MAX_ITEMS) {
    throw new IllegalStateException(
            "Cart capacity exceeded"
    );
}
```

# مرحله 9 — Advanced Tests

فایل `ShoppingCartAdvancedTest.java` و سناریوهای:

```text
empty cart
remove missing
decimal
large finite
discount below/exactly/above 100
add → update → remove
duplicate
invalid name/price
capacity
```

# مرحله 10 — Final JaCoCo

```bash
mvn clean verify
```

Baseline/Final Line, Branch, Method را مقایسه کن.

# مرحله 11 — PIT

```bash
mvn org.pitest:pitest-maven:mutationCoverage
```

Generated/Killed/Survived/Mutation Score را ذخیره کن.

# مرحله 12 — Surviving Mutants

اول mutant واقعی را ببین؛ سپس تست مناسب اضافه کن و PIT را دوباره اجرا کن.

# مرحله 13 — Codex

۱۲ تعامل را در طول کار ثبت کن: baseline، duplicate، invalid data، failure analysis، update tests، compile failure، refactor، business feature، advanced tests، JaCoCo، PIT، final review.

## مقصد تقریبی ShoppingCart.java

**این را یکجا paste نکن.**

```java
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private static final int MAX_ITEMS = 100;

    private final Map<String, Double> items = new HashMap<>();

    public void addItem(String name, double price) {
        validateName(name);
        validatePrice(price);

        if (items.containsKey(name)) {
            throw new IllegalArgumentException(
                    "Item already exists: " + name
            );
        }

        if (items.size() >= MAX_ITEMS) {
            throw new IllegalStateException(
                    "Cart capacity exceeded"
            );
        }

        items.put(name, price);
    }

    public boolean removeItem(String name) {
        if (items.containsKey(name)) {
            items.remove(name);
            return true;
        }
        return false;
    }

    public double getTotal() {
        double total = 0.0;
        for (double price : items.values()) {
            total += price;
        }
        return total;
    }

    public double getTotalWithDiscount() {
        double total = getTotal();
        if (total > 100) {
            return total * 0.9;
        }
        return total;
    }

    public int getItemCount() {
        return items.size();
    }

    public void updateItemPrice(String name, double newPrice) {
        validateName(name);
        validatePrice(newPrice);

        if (items.containsKey(name)) {
            items.put(name, newPrice);
        }
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "Item name must not be blank"
            );
        }
    }

    private void validatePrice(double price) {
        if (!Double.isFinite(price) || price <= 0.0) {
            throw new IllegalArgumentException(
                    "Price must be a positive finite value"
            );
        }
    }
}
```

کاری که همین الان انجام بده:

```text
1. pom.xml را بساز.
2. mvn clean test را اجرا کن.
3. اگر 4 تست baseline اجرا و pass شدند، screenshot بگیر.
4. mvn clean verify را اجرا کن.
5. JaCoCo اولیه را ذخیره کن.
6. هنوز ShoppingCart.java را تغییر نده.
```

---

## کاربر

فایل markdown کل این چتمون رو بهم بده
