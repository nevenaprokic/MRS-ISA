import { Grid, Box} from "@mui/material";
import "./CottageProfilePage.scss";
import MeetingRoomIcon from '@mui/icons-material/MeetingRoom';
import HotelIcon from '@mui/icons-material/Hotel';
import MeetingRoom from "@mui/icons-material/MeetingRoom";
import ArticleIcon from '@mui/icons-material/Article';
import ShowMoreText from "react-show-more-text";

function AdditionalDescriptionBox(){
    let description = "aaaaadkks anldkbj asbfjk safbjksbf akjbfsz vaskjfjahfdiqfnv hfasndasfaskfh fhfkasf asfkeifanfkzsvnaskhfipe hahsdsha sahd asdha  dhsadsa ahsdsah sahdhsa hsafha hafhafahfa ahfahsfhashf ahdfhasfhsa ahfa fhhf ahfhashfah afhfahsfhas ahfsahfhsahf hsadsa ahsdsah sahdhsa hsafha hafhafahfa ahfahsfhashf ahdfhasfhsa ahfa fhhf ahfhashfah afhfahsfhas ahfsahfhsahf ashfahf ashfahf"
    function executeOnClick(isExpanded) {
        console.log(isExpanded);
    }

    return(
        <div className="aditioanlInfoContainer">
                <div>
        
                    <div className="basicBoxItem">
                        <ArticleIcon color="action"/>
                    </div>
                    <label className="basicBoxItemTitle">Rules of coduct: </label>
                        
                    <div className="descriptionText">
                        <ShowMoreText
                        
                            lines={4}
                            more="Show more"
                            less="Show less"
                            className="content-css"
                            anchorClass="my-anchor-css-class"
                            onClick={executeOnClick}
                            expanded={false}
                            width={280}
                            truncatedEndingComponent={"... "}
                        >
                            {description}
                        </ShowMoreText>
                    </div>
                </div>
                <br></br>
                <div>
        
                    <div className="basicBoxItem">
                        <ArticleIcon color="action"/>
                    </div>
                    <label className="basicBoxItemTitle">Cancellation conditions: </label>
                        
                    <div className="descriptionText">
                        <ShowMoreText
                        
                            lines={4}
                            more="Show more"
                            less="Show less"
                            className="content-css"
                            anchorClass="my-anchor-css-class"
                            onClick={executeOnClick}
                            expanded={false}
                            width={280}
                            truncatedEndingComponent={"... "}
                        >
                            {description}
                        </ShowMoreText>
                    </div>
                </div>
        </div>
    );
            
             



}

export default AdditionalDescriptionBox;