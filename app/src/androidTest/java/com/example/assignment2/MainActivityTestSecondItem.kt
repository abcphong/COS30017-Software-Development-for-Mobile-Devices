package com.example.assignment2


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTestSecondItem {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun mainActivityTestSecondItem() {
        val materialButton = onView(
            allOf(
                withId(R.id.nextButton), withText("Next"),
                childAtPosition(
                    allOf(
                        withId(R.id.main),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

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
                withId(R.id.itemID), withText("2"),
                withParent(
                    allOf(
                        withId(R.id.main),
                        withParent(withId(android.R.id.content))
                    )
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("2")))

        val textView3 = onView(
            allOf(
                withId(R.id.instrumentName), withText("Keyboard"),
                withParent(
                    allOf(
                        withId(R.id.main),
                        withParent(withId(android.R.id.content))
                    )
                ),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("Keyboard")))

        val radioButton = onView(
            allOf(
                withText("Sustain Pedal"),
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
                withText("Bench"),
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
                withId(R.id.instrumentPrice), withText("Price: 40 credits"),
                withParent(
                    allOf(
                        withId(R.id.main),
                        withParent(withId(android.R.id.content))
                    )
                ),
                isDisplayed()
            )
        )
        textView4.check(matches(withText("Price: 40 credits")))

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

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
