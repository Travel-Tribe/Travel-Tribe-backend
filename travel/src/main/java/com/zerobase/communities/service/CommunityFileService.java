package com.zerobase.communities.service;

import com.zerobase.communities.entity.CommunityFileEntities;
import com.zerobase.communities.type.CommunityFileDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface CommunityFileService {


    List<CommunityFileDto> getFiles(long communityId);

    List<CommunityFileDto> saveFiles(long communityId, List<String> fileNames);



    void deleteAllByCommunityId(long communityId);
}
