import "./CottageProfilePage.scss";
import ArticleIcon from "@mui/icons-material/Article";
import ShowMoreText from "react-show-more-text";

function AdditionalDescriptionBox({ additionData }) {
  function executeOnClick(isExpanded) {}

  return (
    <div className="aditioanlInfoContainer">
      <div>
        <div className="basicBoxItem">
          <ArticleIcon color="action" />
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
            {additionData.rulesOfConduct}
          </ShowMoreText>
        </div>
      </div>
      <br></br>
      <div>
        <div className="basicBoxItem">
          <ArticleIcon color="action" />
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
            {additionData.cancellationConditions}
          </ShowMoreText>
        </div>
      </div>
    </div>
  );
}

export default AdditionalDescriptionBox;
