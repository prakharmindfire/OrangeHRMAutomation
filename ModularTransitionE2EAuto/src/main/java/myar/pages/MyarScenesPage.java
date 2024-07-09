package myar.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static com.qa.util.TestUtil.*;

import com.qa.base.TestBase;

import net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable;

public class MyarScenesPage extends TestBase
{
	@FindBy(xpath = "//button[@data-testid='create-new-scene']")
	WebElement NewScene_Button;
	
	@FindBy(xpath = "//div//input[@data-testid='addSceneName']")
	WebElement EnterSceneName_textBox;
	
	@FindBy(xpath = "//button[@data-testid='saveBtnTestId']")
	WebElement addSceneSave_button;
	
	@FindBy(xpath = "(//i[@class='fas fa-copy'])")
	WebElement copy_scene_action;

	@FindBy(xpath = "//input[@class='form-control form-input']")
	WebElement copyScNameTxtBox;
	
	@FindBy(xpath = "//button[@data-testid='saveBtnTestId']")
	WebElement copyScn_CopyButton;
	
	@FindBy(xpath = "//button[@data-testid='load-scene-from-library']")
	WebElement SelectSceneButton;
	
	
	String sceneName ="(//div[@class='scene-name']//span[@triggers='hover'])[xxxxx]";
	
	
	// Initializing the Page Factory/Objects:
		public MyarScenesPage() 
		{
			PageFactory.initElements(getdriver(), this); // "this" means current class object. All the above variables will
			// be initialized with this driver
		}

		/* FUNCTIONS */
		public void clickOnSelectSceneButton()
		{
			clickElement(SelectSceneButton, "SELECT SCENE button");
		}
		public void enterCopySceneName(String cpyScName)
		{
			clearAndInput(copyScNameTxtBox, "Name textbox of COPY SCENE modal", cpyScName);
		}
		public void clickOnCopyScene()
		{
			threadMethod(2000);
			clickElement(copy_scene_action, "Copy scene action");
		}
		public String  getSceneName(int sceneIndex)
		{
			threadMethod(2000);
			return retrieveText(prepareWebElementWithDynamicXpath(sceneName,String.valueOf(sceneIndex)),"Scene name");
		}
		public void createScene(String sceneName)
		{
			clickOnNewSceneButton();
			enterSceneName(sceneName);
			clickOnSaveBtnAddScene();
			threadMethod(5000);
		}
		public void clickOnNewSceneButton()
		{
			clickElement(NewScene_Button, "NEW SCENE button");
			threadMethod(2000);
		}
		public void clickOnSaveBtnAddScene()
		{
			clickElement(addSceneSave_button, "SAVE button of ADD SCENE modal");
		}
		
		public void enterSceneName(String sceneName)
		{
			clearAndInput(EnterSceneName_textBox, "Enter scene name text box", sceneName);
			threadMethod(2000);
		}
		
		public void clickOn_CopyScn_CopyButton()
		{
			clickElement(copyScn_CopyButton, "COPY button of COPY SCENE modal");
		}
		
		public void copyScene(String cpyScName)
		{
			clickOnCopyScene();
			enterCopySceneName(cpyScName);
			clickOn_CopyScn_CopyButton();
		}
}
