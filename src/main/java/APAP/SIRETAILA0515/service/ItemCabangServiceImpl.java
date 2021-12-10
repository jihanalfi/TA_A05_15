package APAP.SIRETAILA0515.service;

import APAP.SIRETAILA0515.model.CabangModel;
import APAP.SIRETAILA0515.model.ItemCabangModel;
import APAP.SIRETAILA0515.repository.CabangDb;
import APAP.SIRETAILA0515.repository.ItemCabangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemCabangServiceImpl implements ItemCabangService{
    @Autowired
    private ItemCabangDb itemCabangDb;

    @Autowired
    CabangDb cabangDb;

    @Override
    public List<ItemCabangModel> retrieveItemByCabang(CabangModel cabang){
        return itemCabangDb.findAllByCabang(cabang);
    }

    @Override
    public ItemCabangModel getItemById(Long Id){
        return itemCabangDb.getById(Id);
    }


    @Override
    public void addItem(ItemCabangModel item) {
        itemCabangDb.save(item);
    }

    @Override
    public void addItemCabang(ItemCabangModel cabangModel){
        itemCabangDb.save(cabangModel);
    }

    @Override
    public ItemCabangModel findByUuid(String uuid){
        Optional<ItemCabangModel> item = itemCabangDb.findByUuidItem(uuid);
        if(item.isPresent()){
            return item.get();
        } else {
            return null;
        }
    }
}
