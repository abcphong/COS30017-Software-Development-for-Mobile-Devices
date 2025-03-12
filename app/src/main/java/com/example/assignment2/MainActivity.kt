package com.example.assignment2


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import android.util.Log


//view model for rental item
class RentalViewModel : ViewModel() {
    val rentalItems = listOf(
        RentalItem(1,
            R.drawable.guitar,
            "Electric Guitar",
            4.5f, listOf("With Amp", "Extra Strings"),
            50,
            "A high-quality electric guitar, perfect for rock and blues players. Comes with optional accessories like an amplifier and extra strings for a complete setup."),
        RentalItem(2,
            R.drawable.keyboard,
            "Keyboard",
            4.2f,
            listOf("Sustain Pedal", "Bench"),
            40,
            "A versatile electronic keyboard suitable for beginners and professionals alike. Includes optional sustain pedal and a comfortable bench for better playing experience."),
        RentalItem(3,
            R.drawable.drum,
            "Drum Set",
            5.0f,
            listOf("Sticks Included", "Cymbals"),
            60,
            "A full drum kit designed for energetic drummers. Comes with sticks and optional cymbals to enhance your rhythm and beats."),
        RentalItem(4,
            R.drawable.cello,
            "Cello",
            4.5f,
            listOf("Sticks Included","Bench"),
            120,
            "A beautifully crafted cello with rich tones, ideal for classical and orchestral performances. Optional bench and bow included for comfortable playing.")
    )
    var currentIndex = 0 // Track the current item
    // get current item
    fun getCurrentItem(): RentalItem {
        return rentalItems[currentIndex]
    }
    // move to next item
    fun nextItem() {
        currentIndex = (currentIndex + 1) % rentalItems.size
    }
}
// Main Clas
class MainActivity : AppCompatActivity() {
    lateinit var viewModel: RentalViewModel
    private  val  BORROW_REQUEST_CODE = 1
    private val TAG = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        Log.d(TAG, "onCreate: MainActivity started")
        //initialize
        viewModel = ViewModelProvider(this).get(RentalViewModel::class.java)
        val borrowButton: Button = findViewById(R.id.borrowButton)
        val itemImage = findViewById<ImageView>(R.id.instrumentImage)
        val itemID = findViewById<TextView>(R.id.itemID)
        val itemName = findViewById<TextView>(R.id.instrumentName)
        val itemRating = findViewById<RatingBar>(R.id.instrumentRate)
        val itemPrice = findViewById<TextView>(R.id.instrumentPrice)
        val nextButton = findViewById<Button>(R.id.nextButton)
        val attributeGroup = findViewById<RadioGroup>(R.id.instrumentAttributeGroup)
        val itemDescription = findViewById<TextView>(R.id.instrumentDescription)


        // update UI with current item
        fun updateUI() {
            val currentItem = viewModel.getCurrentItem()
            itemImage.setImageResource(currentItem.imageRes) // Update image
            itemID.text = currentItem.ID.toString() // Update ID number
            itemName.text = currentItem.name // Update name
            itemRating.rating = currentItem.rating // Update rating
            itemPrice.text = "Price: ${currentItem.price} credits" // Update price
            itemDescription.text = currentItem.description // Update description


            //updates attribute dynamically
            attributeGroup.removeAllViews()
            for(attribute in currentItem.attributes)
            {
                Log.d(TAG, "Selected Attribute: $currentItem.attributes")
                val radioButton = RadioButton(this)
                radioButton.text = attribute
                attributeGroup.addView(radioButton)
            }
        }


        // Show first item when activity starts
        updateUI()


        // Change to next item when "Next" button is clicked
        nextButton.setOnClickListener {
            Log.d(TAG, "Next button clicked")
            viewModel.nextItem()
            updateUI()
        }
        //Borrow button
        borrowButton.setOnClickListener{
            val intent = Intent(this, BorrowActivity::class.java)
            Log.d(TAG, "Borrow button clicked")
            val selectedRadioButton = findViewById<RadioButton>(attributeGroup.checkedRadioButtonId)
            val selectedAttribute = selectedRadioButton?.text?.toString() ?: "No attribute selected"
            Log.d(TAG, "Selected Attribute: $selectedAttribute")


            intent.putExtra("rentalItem",viewModel.getCurrentItem()) //Pass the Rental Item
            intent.putExtra("selectedAttribute",selectedAttribute) // Pass selected attribute or ("No attribute selected")
            startActivityForResult(intent,BORROW_REQUEST_CODE)
        }
    }
    // Activity result callback
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "onActivityResult: requestCode=$requestCode, resultCode=$resultCode")
        if (requestCode == BORROW_REQUEST_CODE && resultCode == RESULT_OK) {
            val savedItem = data?.getParcelableExtra<RentalItem>("savedItem")
            Log.d(TAG, "Item saved: ${savedItem?.name}")
            Toast.makeText(this, "${savedItem?.name} saved!", Toast.LENGTH_SHORT).show()
        }
        else
        {
            Log.d(TAG, "Borrowing action canceled or failed")
        }
    }
}


