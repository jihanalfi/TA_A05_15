package APAP.SIRETAILA0515.service;

import APAP.SIRETAILA0515.model.CabangModel;
import APAP.SIRETAILA0515.model.ItemCabangModel;
import APAP.SIRETAILA0515.repository.ItemCabangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ItemCabangServiceImpl implements ItemCabangService{
    @Autowired
    private ItemCabangDb itemCabangDb;

    @Override
    public List<ItemCabangModel> retrieveItemByCabang(CabangModel cabang){
        return itemCabangDb.findAllByCabang(cabang);
    }

    @Override
    public ItemCabangModel getItemById(Long Id){
        return itemCabangDb.getById(Id);
    }
}
