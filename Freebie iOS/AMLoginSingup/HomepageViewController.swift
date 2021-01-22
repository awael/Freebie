//
//  HomepageViewController.swift
//  AMLoginSingup
//
//  Created by CSE on 1/22/21.
//  Copyright © 2021 amirs.eu. All rights reserved.
//

import UIKit

class HomepageViewController: UIViewController {
    
    
    @IBOutlet weak var collectionView: UICollectionView!
    let dataArray = ["Clothes", "Food", "Blankets", "First Aid", "Furniture", "Gadgets", "Medicine", "Bags", "Sanitary", "Supplies","Toys","Utensils","Sports","Other"]
    
    var estimateWidth = 160.0
    var cellMarginSize = 16.0
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Set Delegates
        self.collectionView.delegate = self
        self.collectionView.dataSource = self
        
        
        
        // Register cells
        self.collectionView.register(UINib(nibName: "ItemCell", bundle: nil), forCellWithReuseIdentifier: "ItemCell")
        
        // SetupGrid view
        self.setupGridView()
    }
    
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        
        self.setupGridView()
        DispatchQueue.main.async {
            self.collectionView.reloadData()
        }
    }
    
    
    func setupGridView() {
        let flow = collectionView?.collectionViewLayout as! UICollectionViewFlowLayout
        flow.minimumInteritemSpacing = CGFloat(self.cellMarginSize)
        flow.minimumLineSpacing = CGFloat(self.cellMarginSize)
    }
    
    func collectionView(_ collectionView: UICollectionView,
                        didSelectItemAt indexPath: IndexPath) {
        print("Cell \(indexPath.row + 1) clicked")
        
        let vc = storyboard?.instantiateViewController(withIdentifier: "itemvc") as! itemController
        present(vc,animated: true)
        
    }
}

extension HomepageViewController: UICollectionViewDataSource {
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return self.dataArray.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "ItemCell", for: indexPath) as! ItemCell
        cell.setData(text: self.dataArray[indexPath.row])
        
        return cell
    }
}

extension HomepageViewController: UICollectionViewDelegateFlowLayout {
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        let width = self.calculateWith()
        return CGSize(width: width, height: width)
    }
    
    func calculateWith() -> CGFloat {
        let estimatedWidth = CGFloat(estimateWidth)
        let cellCount = floor(CGFloat(self.view.frame.size.width / estimatedWidth))
        
        let margin = CGFloat(cellMarginSize * 2)
        let width = (self.view.frame.size.width - CGFloat(cellMarginSize) * (cellCount - 1) - margin) / cellCount
        
        return width
    }
}
