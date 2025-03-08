package com.example.assignment2


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTestFirstItem {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun mainActivityTestFirstItem() {
        val textView = onView(
            allOf(
                withId(R.id.title), withText("Welcome to Swinburne musical instruments studio"),
                withParent(
                    allOf(
                        withId(R.id.main),
                        withParent(withId(android.R.id.content))
                    )
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Welcome to Swinburne musical instruments studio")))

        val imageView = onView(
            allOf(
                withId(R.id.instrumentImage), withContentDescription("Electric Guitar"),
                withParent(
                    allOf(
                        withId(R.id.main),
                        withParent(withId(android.R.id.content))
                    )
                ),
                isDisplayed()
            )
        )
        imageView.check(matches(isDisplayed()))

        val textView2 = onView(
            allOf(
                withId(R.id.itemID), withText("1"),
                withParent(
                    allOf(
                        withId(R.id.main),
                        withParent(withId(android.R.id.content))
                    )
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("1")))

        val textView3 = onView(
            allOf(
                withId(R.id.instrumentName), withText("Electric Guitar"),
                withParent(
                    allOf(
                        withId(R.id.main),
                        withParent(withId(android.R.id.content))
                    )
                ),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("Electric Guitar")))

        val radioButton = onView(
            allOf(
                withText("With Amp"),
                withParent(
                    allOf(
                        withId(R.id.instrumentAttributeGroup),
                        withParent(withId(R.id.main))
                    )
                ),
                isDisplayed()
            )
        )
        radioButton.check(matches(isDisplayed()))

        val radioButton2 = onView(
            allOf(
                withText("Extra Strings"),
                withParent(
                    allOf(
                        withId(R.id.instrumentAttributeGroup),
                        withParent(withId(R.id.main))
                    )
                ),
                isDisplayed()
            )
        )
        radioButton2.check(matches(isDisplayed()))

        val textView4 = onView(
            allOf(
                withId(R.id.instrumentPrice), withText("Price: 50 credits"),
                withParent(
                    allOf(
                        withId(R.id.main),
                        withParent(withId(android.R.id.content))
                    )
                ),
                isDisplayed()
            )
        )
        textView4.check(matches(withText("Price: 50 credits")))

        val button = onView(
            allOf(
                withId(R.id.nextButton), withText("Next"),
                withParent(
                    allOf(
                        withId(R.id.main),
                        withParent(withId(android.R.id.content))
                    )
                ),
                isDisplayed()
            )
        )
        button.check(matches(isDisplayed()))

        val button2 = onView(
            allOf(
                withId(R.id.borrowButton), withText("Borrow"),
                withParent(
                    allOf(
                        withId(R.id.main),
                        withParent(withId(android.R.id.content))
                    )
                ),
                isDisplayed()
            )
        )
        button2.check(matches(isDisplayed()))

        val button3 = onView(
            allOf(
                withId(R.id.borrowButton), withText("Borrow"),
                withParent(
                    allOf(
                        withId(R.id.main),
                        withParent(withId(android.R.id.content))
                    )
                ),
                isDisplayed()
            )
        )
        button3.check(matches(isDisplayed()))
    }
}
