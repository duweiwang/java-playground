/*
 * The MIT License
 * Copyright © 2014-2021 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.wangduwei.swing.fileselector;

/**
 * The Model-View-Presenter(MVP) architectural pattern, helps us achieve what is called "The
 * separation of concerns" principle. This is accomplished by separating the application's logic
 * (Model), GUIs (View), and finally the way that the user's actions update the application's logic
 * (Presenter).
 *
 * <p>In the following example, The {@link FileLoader} class represents the app's logic, the {@link
 * FileSelectorJFrame} is the GUI and the {@link FileSelectorPresenter} is responsible to respond to
 * users' actions.
 *
 * <p>Finally, please notice the wiring between the Presenter and the View and between the
 * Presenter and the Model.
 */
public class App {

  /**
   * Program entry point.
   *
   * @param args command line args
   */
  public static void main(String[] args) {
    FileLoader loader = new FileLoader();
    FileSelectorView frame = new FileSelectorJFrame();
    FileSelectorPresenter presenter = new FileSelectorPresenter(frame);
    presenter.setLoader(loader);
    presenter.start();
  }
}
