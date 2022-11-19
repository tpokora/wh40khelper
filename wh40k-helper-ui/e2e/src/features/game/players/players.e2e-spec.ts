import {browser, by, element, logging} from 'protractor';
import {AppPage} from "../../../app.po";

describe('workspace-project App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    } as logging.Entry));
  });

  it('inputs should have players names and CP', async () => {
    await page.navigateTo();
    let playerOneNameInput = element(by.id('player-one-name'));
    let playerTwoNameInput = element(by.id('player-two-name'));
    let playerOneCpInput = element(by.id('player-one-cp'));
    let playerTwoCpInput = element(by.id('player-two-cp'));

    playerOneNameInput.sendKeys('Player One');
    playerTwoNameInput.sendKeys('Player Two');
    playerOneCpInput.sendKeys('8');
    playerTwoCpInput.sendKeys('9');

    expect(await playerOneNameInput.getAttribute('value')).toBe('Player One');
    expect(await playerTwoNameInput.getAttribute('value')).toBe('Player Two');
    expect(await playerOneCpInput.getAttribute('value')).toBe('8');
    expect(await playerTwoCpInput.getAttribute('value')).toBe('9');
  });
});
