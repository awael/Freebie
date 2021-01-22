//
//  itemCell.swift
//  AMLoginSingup
//
//  Created by CSE on 1/22/21.
//  Copyright © 2021 amirs.eu. All rights reserved.
//

import UIKit

class ItemCell: UICollectionViewCell {
    
    
    @IBOutlet weak var textLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }
    
    func setData(text: String){
        self.textLabel.text = text
    }
    
}
