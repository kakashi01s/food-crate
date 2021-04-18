package food.order.delivery.online.offers.deals.coupons.viewpager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import food.order.delivery.online.offers.deals.coupons.view.fragment.CantonantFragment
import food.order.delivery.online.offers.deals.coupons.view.fragment.CategoryFragment
import food.order.delivery.online.offers.deals.coupons.view.fragment.FragmentHome
import food.order.delivery.online.offers.deals.coupons.view.fragment.DealFragment

class AppPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    val NUM_ITEMS = 4;

    override fun getCount(): Int {
        return NUM_ITEMS
    }

    override fun getItem(position: Int): Fragment {
        when (position){
            0 -> {
                return DealFragment.newInstance(position, "Deals")
            }
            1 -> {
                return CantonantFragment.newInstance(position, "Deals")
            }
            2 -> {
                return FragmentHome.newInstance(position, "Home")
            }
            3 -> {
                return CategoryFragment.newInstance(position, "Category")
            }

            else -> return FragmentHome.newInstance(position, "Home")
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title : String? = null;
        if (position == 0) {
            title = "Deals"
        } else if (position == 1) {
            title = "Recipe"
        }
        else if (position == 2) {
            title = "Home"
        }
        else if (position == 3) {
            title = "Category"
        }
        return title
    }
}