from selenium import webdriver
import time
from selenium.webdriver.common.by import By


driver_location = "/snap/bin/chromium.chromedriver"
binary_location = "/usr/bin/chromium-browser"

options = webdriver.ChromeOptions()
options.binary_location = binary_location

driver = webdriver.Chrome(executable_path=driver_location, chrome_options=options)
driver.maximize_window()
driver.get("https://www.amazon.in")

time.sleep(3)
login_button = driver.find_element(By.ID,"nav-link-accountList")

login_button.send_keys('ChromeDriver')

login_button.click()

time.sleep(3)

phone_field = driver.find_element(By.ID, "ap_email")
phone_field.send_keys("123")

submit_button = driver.find_element(By.ID,"continue")
submit_button.click()

if "Incorrect phone number" in driver.page_source:
    print("Passed")
else:
    print("failed")