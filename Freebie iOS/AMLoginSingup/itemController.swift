//
//  itemController.swift
//  AMLoginSingup
//
//  Created by CSE on 1/22/21.
//  Copyright © 2021 amirs.eu. All rights reserved.
//

import UIKit

class itemController: UIViewController {
    @IBOutlet weak var picker: UIPickerView!
    var pickerData: [String] = [String]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
       picker.dataSource=self
        picker.delegate=self
       pickerData = ["Resala", "Masr El-Khier", "Helm", "Orman", "Egyptian Food Bank", "75375"]
    }
  
//    
//    override func didReceiveMemoryWarning() {
//        super.didReceiveMemoryWarning()
//        // Dispose of any resources that can be recreated.
//    }
//    
//
//    /*
//    // MARK: - Navigation
//
//    // In a storyboard-based application, you will often want to do a little preparation before navigation
//    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
//        // Get the new view controller using segue.destinationViewController.
//        // Pass the selected object to the new view controller.
//    }
//    */

}
extension itemController:UIPickerViewDataSource{
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
  func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return 6
    }
    

}
extension itemController:UIPickerViewDelegate{
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return pickerData[row]
    }
}
