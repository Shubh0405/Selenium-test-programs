from selenium import webdriver
from selenium.webdriver.common.by import By
import time
from selenium.webdriver.common.desired_capabilities import DesiredCapabilities
from selenium.common.exceptions import NoSuchElementException

from selenium.webdriver.support.ui import Select


driver_location = "/snap/bin/chromium.chromedriver"
binary_location = "/usr/bin/chromium-browser"

options=webdriver.ChromeOptions()
options.binary_location=binary_location

caps=DesiredCapabilities().CHROME
caps["pageLoadStrategy"] = "eager"

driver = webdriver.Chrome(desired_capabilities=caps,executable_path=driver_location, options=options)
driver.maximize_window()

def start_flipkart_script():

    driver.get("https://www.flipkart.com")
    time.sleep(3)

    ############# 1. verify Title Login
    check_login_text()

    ############# 2. verify username
    check_username_field()

    ############# 3. verify password
    check_password_field()

    ############# 4. click on close button
    click_close_button()

    ############# 5. verify top categories in the menu
    verify_top_categories()

    ############# 6. click on any category in the menu
    click_mobile_category()
    time.sleep(3)

    ############# 7. click on any item eg: mobile
    click_first_phone()
    time.sleep(3)

    ############# 8. verify title filter on left nav bar
    check_filter_title()

    ############# 9. update the price to 2000
    set_price()

    ############# 10. scroll down to the bottom of page and verify Need help?
    scroll_and_find_need_help()
    time.sleep(3)

    driver.quit()



def check_login_text():
    login_text_element = driver.find_element(By.CLASS_NAME, "_36KMOx").find_element(By.TAG_NAME, "span")

    if "Login" in login_text_element.text:
        print("Passed: Login test")
    else:
        driver.save_screenshot("Selenium-test-programs/screenshots/check_login_text_failed.png")
        print("Failed: Login test")
    
    return

def check_username_field():

    try:
        username_field = driver.find_element(By.XPATH, "//span[text()='Enter Email/Mobile number']")
        print("Username check Passed")
    except NoSuchElementException:
        driver.save_screenshot("Selenium-test-programs/screenshots/check_username_field_failed.png")
        print("Username check failed")

    return

def check_password_field():

    try:
        password_field = driver.find_element(By.XPATH, "//span[text()='Enter Password']").is_displayed()
        print("Password check Passed")
    except NoSuchElementException:
        driver.save_screenshot("Selenium-test-programs/screenshots/check_password_field_failed.png")
        print("Password check failed")

    return



def click_close_button():

    try:
        close_button = driver.find_element(By.XPATH,"//button[text()='✕']").click()
        print("Close Button Clicked")
    except NoSuchElementException:
        print("Close Button not found!")

def verify_top_categories():

    try:
        top_categories_text = driver.find_element(By.CLASS_NAME, "_37M3Pb").find_elements(By.XPATH,"//div/a/div[2]")

        # print(top_categories_text)
        top_categories_dict = {"Top Offers":1, "Mobiles & Tablets": 1,"Electronics": 1, "Fashion": 1, "Beauty": 1, "Home & Furniture": 1, "Appliances": 1, "Travel": 1, "Grocery": 1}

        for cat in top_categories_text:
            if cat.text != "":
                if cat.text in top_categories_dict:
                    del top_categories_dict[cat.text]
                else:
                    print("Top Categories Check failed! Extra Category found: " + cat.text)
                    driver.save_screenshot("Selenium-test-programs/screenshots/top_categories_check_failed.png")
                    return 

        if len(top_categories_dict.values()):
            driver.save_screenshot("Selenium-test-programs/screenshots/top_categories_check_failed.png")
            print("Top categories check failed! Categories not found: " + top_categories_dict.values())
            return

        print("Top Categories check passed!")

    except NoSuchElementException:
        print("Top Categories not found!")

    return

def click_mobile_category():

    try:
        mobile_category = driver.find_element(By.CLASS_NAME, "_37M3Pb").find_element(By.XPATH, "//div[2]/a/div[2][text()='Mobiles & Tablets']")
        mobile_category.click()
        print("Mobile Category Clicked")
    except NoSuchElementException:
        print("Mobile Category Not Found!")

    return

def click_first_phone():

    try:
        first_phone = driver.find_element(By.CLASS_NAME, "_6t1WkM").find_element("xpath", "/html/body/div/div/div[3]/div[3]/div/div[1]")
        first_phone.click()
        print("First phone clicked")
    except NoSuchElementException:
        print("First phone element not found")

    return

def check_filter_title():

    try:
        title_filter = driver.find_element(By.XPATH,"//section/div/div/span[text()='Filters']")
        print("Filter title passed")
    except NoSuchElementException:
        driver.save_screenshot("Selenium-test-programs/screenshots/filter_title_check_failed.png")
        print("Filter title failed")

    return

def set_price():

    try:
        price_field = Select(driver.find_element("xpath", "/html/body/div[1]/div/div[3]/div/div[1]/div/div[1]/div/section[2]/div[4]/div[3]/select"))
        price_field.select_by_visible_text("₹20000")
        print("Price field changed!")
    except NoSuchElementException:
        driver.save_screenshot("Selenium-test-programs/screenshots/set_price_check_failed.png")
        print("Price field not found!")

def scroll_and_find_need_help():

    try:
        need_help = driver.find_element(By.XPATH,"//a[@title='Buying Guide']/div/span[text()='Need help?']")
        driver.execute_script("arguments[0].scrollIntoView();", need_help)
        time.sleep(3)

        print("Need help passed")

    except NoSuchElementException:
        driver.save_screenshot("Selenium-test-programs/screenshots/need_help_check_failed.png")
        print("Need Help failed!")   

start_flipkart_script()
